import socket
import struct
import os

import tensorflow as tf, sys


def func():
	image_path = "tst.jpg"

	#Read in the image_data
	image_data = tf.gfile.FastGFile(image_path, 'rb').read()

	#Loads label file, strips off carriege return
	label_lines = [line.rstrip() for line 
					in tf.gfile.GFile("retrained_labels.txt")]
	# Unpersists graph from file
	with tf.gfile.FastGFile("retrained_graph.pb", "rb") as f:
		graph_def = tf.GraphDef()
		graph_def.ParseFromString(f.read())
		_ = tf.import_graph_def(graph_def,name='')

		with tf.Session() as sess:
			# Feed the image_data as input to the graph and get first prediction
			softmax_tensor = sess.graph.get_tensor_by_name('final_result:0')

			predictions = sess.run(softmax_tensor, \
				{'DecodeJpeg/contents:0': image_data})

			# Sort to show labels of first prediciton in order of confidence
			top_k = predictions[0].argsort()[-len(predictions[0]):][::-1]

			message = label_lines[top_k[0]]

			# for node_id in top_k:
			# 	human_string = label_lines[node_id]
			# 	score = predictions[0][node_id]
			# 	print('%s (score = %.5f)' % (human_string, score))
	return message


address = ("ip-172-31-36-114.us-east-2.compute.internal", 8080)
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)


s.bind(address)
s.listen(10)


while True:
	client, addr = s.accept()
	buf = ''
	while len(buf)<4:
	    buf += client.recv(4-len(buf)).decode("ISO-8859-1")
	size = struct.unpack('!i', buf.encode("ISO-8859-1"))[0]
	print("receiving %s bytes" % size)

	with open('tst.jpg', 'wb') as f:
		while size > 0:
			data = client.recv(1024)
			f.write(data)
			size -= len(data)
	

	#func()     
	message_to_send = func().encode("utf-8")
	client.sendall(message_to_send)

	client.close()


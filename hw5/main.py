# D:\Python\python.exe
# -*- coding:utf-8 -*-

from Tkinter import *
import os
import Image

def draw():
	image = Image.open(e.get())

	R = {}
	G = {}
	B = {}

	for i in xrange(256):
		R[i] = 0
		G[i] = 0
		B[i] = 0

	x = image.size[0]
	y = image.size[1]

	for i in xrange(x):
		for j in xrange(y):
			rgb = image.getpixel((i, j))
			R[rgb[0]] += 1
			G[rgb[1]] += 1
			B[rgb[2]] += 1
			
	temp_R = sorted(R.items(), key = lambda R:R[1], reverse = True)[0]
	temp_G = sorted(G.items(), key = lambda G:G[1], reverse = True)[0]
	temp_B = sorted(B.items(), key = lambda B:B[1], reverse = True)[0]

	for i in xrange(256):
		R[i] = R[i] * 100 * 2/ temp_R[1]
		G[i] = G[i] * 100 * 2/ temp_G[1]
		B[i] = B[i] * 100 * 2/ temp_B[1]

	image_R = Image.new("RGB", (256, 100 * 2))
	image_G = Image.new("RGB", (256, 100 * 2))
	image_B = Image.new("RGB", (256, 100 * 2))

	for i in xrange(256):
		for j in xrange(100 * 2):
			if j < R[i]:
				image_R.putpixel((i, 100 * 2 - 1 - j), (255, 0, 0))
			else:
				image_R.putpixel((i, 100 * 2 - 1 - j), (0, 0, 0))

			if j < G[i]:
				image_G.putpixel((i, 100 * 2 - 1 - j), (0, 255, 0))
			else:
				image_G.putpixel((i, 100 * 2 - 1 - j), (0, 0, 0))

			if j < B[i]:
				image_B.putpixel((i, 100 * 2 - 1 - j), (0, 0, 255))
			else:
				image_B.putpixel((i, 100 * 2 - 1 - j), (0, 0, 0))

	image_R.save("R.png")
	image_G.save("G.png")
	image_B.save("B.png")
	
	os.system("python window.py")

root = Tk()

Label(root, text = "輸入檔案名稱(需副檔名):").grid(row=0, column=0)

e = Entry(root)
e.grid(row=0, column=1, columnspan=6)

Button(root, text = "確認", command = draw).grid(row=1, column=0)

root.mainloop()
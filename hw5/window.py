# D:\Python\python.exe
# -*- coding:utf-8 -*-

from Tkinter import *
import ImageTk
import os

root = Tk()

Label(root, text = "R:						").pack(side = "top")
imgR = ImageTk.PhotoImage(file = 'R.png')
Label(root, image = imgR).pack(side = "top")

Label(root, text = "G:						").pack(side = "top")
imgG = ImageTk.PhotoImage(file = 'G.png')
Label(root, image = imgG).pack(side = "top")

Label(root, text = "B:						").pack(side = "top")
imgB = ImageTk.PhotoImage(file = 'B.png')
Label(root, image = imgB).pack(side = "top")
Label(root, text = "						").pack(side = "top")

root.mainloop()
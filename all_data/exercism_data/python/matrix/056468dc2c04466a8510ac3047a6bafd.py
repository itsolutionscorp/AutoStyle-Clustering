# -*- coding: utf-8 -*-
"""
Created on Thu Mar 20 08:33:22 2014

@author: hcabral
"""

class Matrix:
	def __init__(self, s):
		rr = s.split("\n")
		rows = []
		for r in rr:
			rows.append([int(i) for i in r.split])
		self.cols = [rows[i][j] for i in range(len(rows)) for j in range(len(rows[0]))]
		self.rows = rows
	
	def rows(self):
		return self.rows
	
	def cols(self):
		return self.cols

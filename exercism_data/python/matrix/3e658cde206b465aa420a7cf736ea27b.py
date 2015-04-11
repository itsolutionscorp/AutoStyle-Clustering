# -*- coding: utf-8 -*-
"""
Created on Thu Mar 20 08:33:22 2014

@author: hcabral
"""

class Matrix:
	def __init__(self, s):
		rows = [[int(i) for i in r.split()] for r in s.split("\n")]
		self.columns = [[rows[i][j] for i in range(len(rows))] for j in range(len(rows[0]))]
		self.rows = rows

#!/usr/bin/env python
from string import maketrans

class DNA(object):
	def __init__(self, prompt):
		self.prompt = prompt

	def to_rna(self):
		intab = "CGTA"
		outtab = "GCAU"
		trantab = maketrans(intab, outtab)
		return self.prompt.translate(trantab)

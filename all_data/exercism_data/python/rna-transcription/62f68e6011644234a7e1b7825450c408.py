#!/usr/bin/env python
# -*- coding: utf-8 -*-

class DNA(object):
	def __init__(self, dna):
		self.dna = dna.upper()

	def to_rna(self):
		return self.dna.replace('T', 'U')

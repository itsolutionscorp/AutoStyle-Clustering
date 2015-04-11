from __future__ import division

class Triangle:
	def __init__(self, s1, s2, s3):
		if max(s1, s2, s3) >=  sum([s1, s2, s3]) / 2:
			raise TriangleError
		match = sum([s1 == s2, s1 == s3, s2 == s3])
		self.type = ['scalene', 'isosceles', 
		             'impossible', 'equilateral'][match]
		
	def kind(self):
		return self.type

class TriangleError (Exception):
	pass

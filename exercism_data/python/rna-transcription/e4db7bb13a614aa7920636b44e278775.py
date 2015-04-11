class DNA(str):
	"""This class represents a string of DNA"""
	
	def to_rna(self):
		"""Transcode this DNA string into RNA"""
		return self.replace("T","U")

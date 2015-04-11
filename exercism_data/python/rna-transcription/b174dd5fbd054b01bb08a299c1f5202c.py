__all__ = ["DNA"]

class DNA(object):
	def __init__(self, proteins):
		self.proteins = proteins

	def to_rna(self):
		return self.proteins.replace("T", "U")

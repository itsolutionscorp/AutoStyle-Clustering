class Series(object):
	def __init__(self, source):
		self.source = [int(i) for i in list(source)]

	def slices(self, len):
		source_len = len(self.source)
		if len < 1 or source_len < len:
			raise ValueError("Invalid slice length for this series: {}".format(len))

		return [self.source[i:i+len] for i in xrange(0, source_len - len + 1)]

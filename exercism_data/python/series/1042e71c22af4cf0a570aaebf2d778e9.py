class Series(object):
	def __init__(self, source):
		self.source = [int(i) for i in list(source)]

	def slices(self, length):
		source_len = len(self.source)
		if length <= 0 or source_len < length:
			raise ValueError("Invalid slice length for this series: {}".format(length))

		slice_list = []
		for offset in xrange(0, source_len - length + 1):
			slice_list.append(self.source[offset:offset + length])
		return slice_list

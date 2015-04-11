class Series(object):
	def __init__(self, seriesString):
		self.seriesString = [int(x) for x in list(seriesString)]

	def slices(self, desiredSlices):
		seriesString = self.seriesString

		slices = []
		if desiredSlices > len(seriesString) or desiredSlices < 1:
			raise ValueError("Invalid slice length for this series: %s" %(desiredSlices))

		for xstring in xrange(0, len(seriesString)+1 - desiredSlices):
			slices.append(seriesString[xstring:xstring+desiredSlices])

		return slices

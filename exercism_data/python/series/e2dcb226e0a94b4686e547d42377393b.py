def slices(numStr,serLen):

	if(len(numStr)<serLen or serLen == 0):
		raise ValueError('Could not generate series of %i with a strlen of %i' % (serLen,len(numStr)))

	finallist = []
	for x in xrange(len(numStr)-serLen+1):
		Templist = []
		for p in xrange(serLen):
			Templist.append(int(numStr[x+p]))
		finallist.append(Templist)
	return finallist

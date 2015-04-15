def proverb(array_of_items, qualifier = ''):
	result = ''
	for n in xrange(0, len(array_of_items) - 1):
		result += 'For want of a {0} the {1} was lost.\n'\
				.format(array_of_items[n],array_of_items[n+1])
	qualifier += ' ' if qualifier else ''
	result += 'And all for the want of a {0}{1}.'.format(qualifier, array_of_items[0])
	return result

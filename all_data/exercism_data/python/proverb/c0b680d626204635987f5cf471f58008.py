def proverb(l, qualifier = ''):
	out = []
	for ix in range(len(l) - 1):
		out.append( 'For want of a {0} the {1} was lost.\n'.format( l[ix], l[ix+1]) )
	if qualifier != '':
		qualifier = qualifier + ' '
	out.append( 'And all for the want of a {0}{1}.'.format( qualifier, l[0]) )
	return ''.join(out)

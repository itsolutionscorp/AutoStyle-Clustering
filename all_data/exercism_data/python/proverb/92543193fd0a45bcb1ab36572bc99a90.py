def proverb(lists,qualifier=''):
	part1='For want of a '
	part2=' was lost.\n'
	part3='And all for the want of a '
	result=''
	i=0
	while i<len(lists)-1:
		result=result+part1+lists[i]+' the '+lists[i+1]+part2
		i+=1
	if len(qualifier) >0:
		#print result+part3+qualifier+' '+lists[0]+'.'
		return result+part3+qualifier+' ' +lists[0]+'.'
	result=result+part3+lists[0]+'.'
	#print result
	return result

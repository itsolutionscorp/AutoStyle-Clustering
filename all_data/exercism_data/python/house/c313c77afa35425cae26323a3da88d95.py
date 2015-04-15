starts = ['lay in', 'ate', 'killed', 'worried', 'tossed', 'milked', 'kissed', 'married', 'woke', 'kept', 'belonged to']

ends = ['house that Jack built', 'malt', 'rat', 'cat', 'dog', 'cow with the crumpled horn', 'maiden all forlorn',
'man all tattered and torn', 'priest all shaven and shorn', 'rooster that crowed in the morn',
'farmer sowing his corn', 'horse and the hound and the horn']

def part(i):
	if i < 1 or i > len(ends):
		i = len(ends)

	i -= 1
	rhyme = 'This is the {}{}{}.'.format(ends[i], '\n' if i > 0 else '',
		'\n'.join('that {} the {}'.format(starts[j], ends[j]) for j in range(i - 1, -1, -1)))
	return rhyme

def rhyme():
	return '\n\n'.join(part(i) for i in range(1, len(ends) + 1))

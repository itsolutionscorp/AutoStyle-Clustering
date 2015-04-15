	
def grid(num):
	if num == '0':
		return ([" _ ", "| |", "|_|", "   "])
	if num== '1':
		return (["   ", "  |", "  |", "   "])
	else:
		raise ValueError,'not implement yet'



def number(lists):
	lists = lists
	check(lists)
	if lists == [" _ ", "| |", "|_|", "   "]:
		return '0'
	if lists == ["   ", "  |", "  |", "   "]:
		return '1'
	else : return '?'



def check(input):
	if len(input) != 4:
		raise ValueError, 'length is not correct'
	for ele in input:
		if len(ele) != 3:
			raise ValueError, 'row too short'

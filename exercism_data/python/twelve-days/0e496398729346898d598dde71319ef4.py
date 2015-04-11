gifts= ['twelve Drummers Drumming',
	'eleven Pipers Piping',
	'ten Lords-a-Leaping',
	'nine Ladies Dancing',
	'eight Maids-a-Milking',
	'seven Swans-a-Swimming',
	'six Geese-a-Laying',
	'five Gold Rings',
	'four Calling Birds',
	'three French Hens',
	'two Turtle Doves',
	'a Partridge in a Pear Tree']
days=[None,'first','second','third','fourth','fifth','sixth',
		'seventh','eighth','ninth','tenth','eleventh','twelfth']
	
def verse(n):
	if n >1:
		attach =', '.join(gifts[-n:-1])
		attach = attach + ', and '+ ''.join(gifts[-1]) 
	else:
		attach = ''.join(gifts[-1])
	return "On the {} day of Christmas my true love gave to me, {}.\n".format(days[n],attach)

def verses(start, end):
	ret = ''
	for n in range(start,end+1):
		ret += verse(n) + '\n'
	return ret
def sing():
	return verses(1,12)

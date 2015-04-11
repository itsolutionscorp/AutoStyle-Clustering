def verse(day):
	gifts = ['twelve Drummers Drumming', 'eleven Pipers Piping', 'ten Lords-a-Leaping', 'nine Ladies Dancing', 'eight Maids-a-Milking', 'seven Swans-a-Swimming', 'six Geese-a-Laying', 'five Gold Rings', 'four Calling Birds', 'three French Hens', 'two Turtle Doves', 'a Partridge in a Pear Tree.\n']
	gifts = gifts[::-1]
	day -= 1
	days ='first second third fourth fifth sixth seventh eighth ninth tenth eleventh twelfth'
	days = days.split()
	res = ["On the %s day of Christmas my true love gave to me" % days[day]]
	while day >= 0:
		res.append(gifts[day])
		if day == 0 and len(res) != 2:
			res[-1]='and '+res[-1]
		day -= 1
	final = ', '.join(res)
	return final
		 
def verses(first,last):
	res = []
	for i in range(first,last+1):
		res.append(verse(i))
	return '\n'.join(res)+'\n'

def sing():
	return verses(1,12)


print verse(3)
print verses(1,3)

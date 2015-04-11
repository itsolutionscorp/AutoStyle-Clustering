def verse(n):
	nums = {
	1: 'first',
	2: 'second',
	3: 'third',
	4: 'fourth',
	5: 'fifth',
	6: 'sixth',
	7: 'seventh',
	8: 'eighth',
	9: 'ninth',
	10: 'tenth',
	11: 'eleventh',
	12: 'twelfth'
	}
	items = {
	1: 'and '*(n>1) + "a Partridge in a Pear Tree",
	2: "two Turtle Doves",
	3: "three French Hens",
	4: "four Calling Birds",
	5: "five Gold Rings",
	6: "six Geese-a-Laying",
	7: "seven Swans-a-Swimming",
	8: "eight Maids-a-Milking",
	9: "nine Ladies Dancing",
	10: "ten Lords-a-Leaping",
	11: "eleven Pipers Piping",
	12: "twelve Drummers Drumming"
	}
	num = nums[n]
	item = 	 ', '.join(items[n-i] for i in range(n)) 
	returnString = 'On the '+num+' day of Christmas my true love gave to me, '+\
	item + '.\n'
	return returnString

def verses(n,m):
	retString = '\n'.join(verse(i) for i in range(n,m+1))
	return retString+'\n'

def sing():
	return verses(1,12)  

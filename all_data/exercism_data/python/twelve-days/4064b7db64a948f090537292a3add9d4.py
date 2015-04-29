start = ['first day',   'second day',  'third day', 
	 'fourth day',  'fifth day' ,  'sixth day',
	 'seventh day', 'eighth day', 'ninth day',
	 'tenth day'  , 'eleventh day', 'twelfth day']

end = [  
	"and a Partridge in a Pear Tree.\n", "two Turtle Doves, "     , "three French Hens, "     ,
	"four Calling Birds, "		   , "five Gold Rings, "      , "six Geese-a-Laying, "    ,
	"seven Swans-a-Swimming, "     , "eight Maids-a-Milking, ",    "nine Ladies Dancing, ",
	"ten Lords-a-Leaping, "        ,  "eleven Pipers Piping, ",  "twelve Drummers Drumming, ",]

template =  "On the {0} of Christmas my true love gave to me, {1}"

def verse(num):
	pre_end =  ''.join(end[:num][::-1])
	if (num == 1):
		pre_end = pre_end.replace('and ', '')
	return template.format(start[num-1], pre_end)

def verses(start, end):
	result = ''
	while(start <= end):
		result += verse(start) + "\n"
		start += 1
	return result

def sing():
	return verses(1, 12)

#print verse(1)
#print verses(1, 3)
#print sing()

whole = [
	"twelve Drummers Drumming",
	"eleven Pipers Piping", 
	"ten Lords-a-Leaping", 
	"nine Ladies Dancing", 
	"eight Maids-a-Milking", 
	"seven Swans-a-Swimming", 
	"six Geese-a-Laying", 
	"five Gold Rings", 
	"four Calling Birds", 
	"three French Hens", 
	"two Turtle Doves", 
	"a Partridge in a Pear Tree"
]

days = [
	'first',
	'second',
	'third',
	'fourth',
	'fifth',
	'sixth',
	'seventh',
	'eighth',
	'ninth',
	'tenth',
	'eleventh',
	'twelfth'
]

def sing():
	return verses(1, 12)

def verse(num):
	part = whole[12-num:]
	if num > 1:
		part[-1] = 'and ' + part[-1]
	begin = "On the {0} day of Christmas my true love gave to me".format(days[num-1])
	part.insert(0, begin)
	return ', '.join(part) + ".\n";

def verses(s_num, e_num):
	return "\n".join([verse(i) for i in range(s_num, e_num+1)]) + "\n"
	
if __name__ == '__main__':
    print(verses(1,3))

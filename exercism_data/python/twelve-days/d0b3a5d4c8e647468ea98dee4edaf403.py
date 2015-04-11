_verses = [
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

_gifts = [
			'a Partridge in a Pear Tree', 
			'two Turtle Doves', 
			'three French Hens', 
			'four Calling Birds', 
			'five Gold Rings', 
			'six Geese-a-Laying', 
			'seven Swans-a-Swimming', 
			'eight Maids-a-Milking', 
			'nine Ladies Dancing', 
			'ten Lords-a-Leaping', 
			'eleven Pipers Piping',
			'twelve Drummers Drumming'
		]
			
			
			
def verse(index):

	if index==1:
		return "On the %s day of Christmas my true love gave to me, %s.\n" % (_verses[index-1], _gifts[index-1])
	else:
		giftlist = _gifts[1:index]
		giftlist.reverse()
		return "On the %s day of Christmas my true love gave to me, %s, and %s.\n" % (_verses[index-1], ", ".join(giftlist), _gifts[0])



def verses(start, end):

	verses_string = []

	for x in range(start, end+1):
		verses_string.append(verse(x) + "\n")


	return "".join(verses_string)


def sing():
	return verses(1, 12)

stuff = ['a shorter song',
         'and a Partridge in a Pear Tree',
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

ordinals = ['zeroth', 'first', 'second', 'third',
             'fourth', 'fifth', 'sixth', 
             'seventh', 'eighth', 'ninth',
              'tenth', 'eleventh', 'twelfth']

def verse(n):
	pre = 'On the ' + ordinals[n] + ' day of Christmas my true love gave to me'
	verse =  ', '.join([pre]+stuff[n:0:-1]) + '.\n'
	if n == 1:
		verse = verse.replace(' and', '')
	return verse
	
def verses(a,b):
	return '\n'.join([verse(i) for i in range(a,b+1)]) + '\n'
	
def sing():
	return verses(1,12)

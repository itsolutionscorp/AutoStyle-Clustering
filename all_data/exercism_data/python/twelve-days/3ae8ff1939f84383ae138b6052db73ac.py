_number_names={1:"first", 2:"second", 3:"third", 4:"fourth", 5:"fifth", 6:"sixth", 7:"seventh", 8:"eighth", 9:"ninth", 10:"tenth", 11:"eleventh", 12:"twelfth"}
_day_gifts={12:"twelve Drummers Drumming,", 11:"eleven Pipers Piping,", 10:"ten Lords-a-Leaping,", 9:"nine Ladies Dancing,", 8:"eight Maids-a-Milking,", 7:"seven Swans-a-Swimming,", 6:"six Geese-a-Laying,", 5:"five Gold Rings,", 4:"four Calling Birds,", 3:"three French Hens,", 2:"two Turtle Doves,", 1:"a Partridge in a Pear Tree.\n"}

def verse(n):
	answer="On the %s day of Christmas my true love gave to me,"%_number_names[n]
	for i in range(n,1,-1):
		answer+=" "+_day_gifts[i]
	answer+=(" " if n==1 else " and ")+_day_gifts[1]
	return answer

def verses(a,b):
	answer=""
	for i in range(a,b+1):
		answer+=verse(i)+"\n"
	return answer

def sing():
	return verses(1,12)


DAYS = ["first", "second", "third", "fourth", "fifth", "sixth",
        "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"]

PRESENTS = ["and a Partridge in a Pear Tree.\n", "two Turtle Doves", "three French Hens",
            "four Calling Birds", "five Gold Rings", "six Geese-a-Laying",
            "seven Swans-a-Swimming", "eight Maids-a-Milking", "nine Ladies Dancing",
            "ten Lords-a-Leaping", "eleven Pipers Piping", "twelve Drummers Drumming"]

VERSE = "On the {} day of Christmas my true love gave to me, {}"

def sing():
    return verses(1, 12)

def verse(n):
    presents = ', '.join(PRESENTS[n-1::-1])    
    if n == 1:
        presents = presents[4:]
    return VERSE.format(DAYS[n-1], presents)

def verses(start, end):
    return '\n'.join(verse(n) for n in range(start, end+1))+'\n'

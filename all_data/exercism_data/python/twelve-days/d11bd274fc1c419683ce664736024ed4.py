import re

numerals = [
        'first', 'second', 'third', 'fourth', 'fifth', 'sixth', 'seventh',
        'eighth', 'ninth', 'tenth', 'eleventh', 'twelfth'
]

thingies = [
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

def verse(n):
    return "On the %s day of Christmas my true love gave to me, %s.\n" % (
        numerals[n-1], re.sub( '(.*,)(.*?)', '\\1 and\\2', ", ".join(thingies[12-n:]) )
    )

def verses(i,j):
    v = ""
    for i in range(i,j+1):
        v += verse(i) + "\n"
    return v

def sing():
    return verses(1,12)

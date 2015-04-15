DAYS = ["first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"]

GIFTS = [
    "a Partridge in a Pear Tree",
    "two Turtle Doves",
    "three French Hens",
    "four Calling Birds",
    "five Gold Rings",
    "six Geese-a-Laying",
    "seven Swans-a-Swimming",
    "eight Maids-a-Milking",
    "nine Ladies Dancing",
    "ten Lords-a-Leaping",
    "eleven Pipers Piping",
    "twelve Drummers Drumming"
]

def verse(num):
    verse = "On the " + DAYS[num-1] + " day of Christmas my true love gave to me, "
    if num > 1: verse += ", ".join([gift for gift in GIFTS[num-1:0:-1]]) + ", and "
    verse += GIFTS[0]
    return verse + ".\n"

def verses(num_start, num_end):
    return "".join([verse(num) + "\n" for num in xrange(num_start, num_end+1)])

def sing():
    return verses(1, 12)

#/usr/bin/python

items = [
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
    "two Turtle Doves"
]

ordinals = [
    "fisrt",
    "second",
    "third",
    "fourth",
    "fifth",
    "sixth",
    "seventh",
    "eighth",
    "ninth",
    "tenth",
    "eleventh",
    "twelfth"
]

def verse(n):
    if n == 1:
        return "On the first day of Christmas my true love gave to me, a Partridge in a Pear Tree.\n"
    else:
        return "On the "+ordinals[n-1]+" day of Christmas my true love gave to me, " + \
               ", ".join(items[(12-n):11]) + \
               ", and a Partridge in a Pear Tree.\n"


def verses(start, end):
    return "\n".join([ verse(n) for n in range(start, end + 1) ]) + "\n"


def sing():
    return verses(1, 12)

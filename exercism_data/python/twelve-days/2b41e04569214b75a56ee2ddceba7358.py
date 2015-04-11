DAYS = [
    "first",
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

GIFTS = [
    "a Partridge in a Pear Tree.\n",
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

LAST_LINE = ", and %s" % GIFTS[0]

def verse(n):
    verse = "On the %s day of Christmas my true love gave to me, " % DAYS[n-1]
    if n == 1:
        return verse + GIFTS[0]
    else:
        return verse + _all_but_last(n) + LAST_LINE

def verses(n, m):
    return "\n".join(verse(i) for i in range(n, m+1)) + "\n"

def sing():
    return verses(1, 12)


def _all_but_last(n):
    return ", ".join(GIFTS[n-1:0:-1])

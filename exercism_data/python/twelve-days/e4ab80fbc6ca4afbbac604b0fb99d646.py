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
    "a Partridge in a Pear Tree.\n"
]

def verse(n):
    verse = "On the " + DAYS[n-1] + " day of Christmas my true love gave to me, "
    if n == 1:
        return verse + GIFTS[-1]
    else:
        return verse + _all_but_last(n) + _last_line()

def verses(n, m):
    return str.join("\n", list([verse(i) for i in range(n, m+1)])) + "\n"

def sing():
    return verses(1, 12)


def _all_but_last(n):
    return str.join(", ", GIFTS[12-n:-1]) + ", "

def _last_line():
    return "and " + GIFTS[-1]

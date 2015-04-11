def sing():
    return verses(1, 12)

def verse(n):
    v = _intro(n)
    v += ''.join([_gift(i, n==1) for i in range(n, 0, -1)])
    v += '\n'
    return v

def verses(start, end):
    return '\n'.join([verse(n) for n in range(start, end + 1)]) + '\n'

def _gift(n, first):
    return "{0}{1} {2}{3}".format("and " if n==1 and not first else "", _cardinals[n-1], _gifts[n-1], "." if n == 1 else ", ")

def _intro(n):
    return "On the {0} day of Christmas my true love gave to me, ".format(_ordinals[n - 1])

_cardinals = [
        "a",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
        "ten",
        "eleven",
        "twelve"
        ]

_ordinals = [
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

_gifts = [
        "Partridge in a Pear Tree",
        "Turtle Doves",
        "French Hens",
        "Calling Birds",
        "Gold Rings",
        "Geese-a-Laying",
        "Swans-a-Swimming",
        "Maids-a-Milking",
        "Ladies Dancing",
        "Lords-a-Leaping",
        "Pipers Piping",
        "Drummers Drumming",
        ]

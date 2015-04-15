things = [
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
    "a Partridge in a Pear Tree",
    ]

nth = "first second third fourth fifth sixth seventh eighth ninth tenth eleventh twelfth".split()

def verse(n):
    gifts = things[-n:]

    if n != 1:
        gifts[-1] = "and " + gifts[-1]

    gifts = ", ".join(gifts)

    return "On the {nth} day of Christmas my true love gave to me, {gifts}.\n".format(nth=nth[n-1], gifts=gifts)

def verses(a, b):
    return ''.join(verse(i) + "\n" for i in range(a, b + 1))

def sing():
    return verses(1, 12)

NTH = ["",
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


GIFT = ["",
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
        "twelve Drummers Drumming",
        ]


def verse(number):
    out = "On the {} day of Christmas my true love gave to me, ".format(NTH[number])
    for i in range(number, 0, -1):
        if i is 1 and number is 1:
            out += "{}.".format(GIFT[i])
        elif i is 1:
            out += "and {}.".format(GIFT[i])
        else:
            out += "{}, ".format(GIFT[i])
    return "{}\n".format(out)


def verses(first, last):
    return ''.join([verse(x) + '\n' for x in range(first, last + 1)])


def sing():
    return verses(1, 12)

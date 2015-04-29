PRESENTS = ("twelve Drummers Drumming",
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
            "a Partridge in a Pear Tree")

ORDINALS = (None,
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
            "twelfth")


def verse(n):
    nth = ORDINALS[n]

    if n == 1:
        presents = PRESENTS[-1]
    else:
        presents = ", and ".join([", ".join(PRESENTS[-n:-1]), PRESENTS[-1]])

    return "On the {} day of Christmas my true love gave to me, {}.\n".format(
        nth, presents)


def verses(start, end):
    return ''.join([verse(n) + "\n" for n in range(start, end + 1)])


def sing():
    return verses(1, 12)

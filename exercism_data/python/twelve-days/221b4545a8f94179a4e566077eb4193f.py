numerals = ["first",
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
            "twelfth"]


def numToNumeral(n):
    return numerals[n-1]

items = ["a Partridge in a Pear Tree",
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
         "twelve Drummers Drumming"]


def gifts(n):
    if n == 1:
        return items[0]
    else:
        return ", ".join(items[n-1:0:-1]) + ", and " + items[0]


def verse(n):
    return "On the {0} day of Christmas my true love gave to me, {1}.\n".format(numToNumeral(n), gifts(n))


def verses(start, end):
    return "\n".join(verse(i) for i in range(start, end+1)) + "\n"


def sing():
    return verses(1, 12)

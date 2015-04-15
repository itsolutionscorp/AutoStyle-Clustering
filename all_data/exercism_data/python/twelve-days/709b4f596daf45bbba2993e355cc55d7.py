item = [
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

ordinal = [
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
    "twelfth",
]

def verse(day):
    items = []
    for i in xrange(day, 1, -1):
        items.append(item[i-1])

    if items:
        item_list = ', '.join(items) + ', and ' + item[0]
    else:
        item_list = item[0]

    return ("On the %s day of Christmas my true love gave to me, %s.\n" %
            (ordinal[day-1], item_list))

def verses(first_day, last_day):
    song = []
    for day in xrange(first_day, last_day+1):
        song.append(verse(day))
    return '\n'.join(song) + "\n"

def sing():
    return verses(1, 12)

gifts = [
    'and a Partridge in a Pear Tree.',
    'two Turtle Doves',
    'three French Hens',
    'four Calling Birds',
    'five Gold Rings',
    'six Geese-a-Laying',
    'seven Swans-a-Swimming',
    'eight Maids-a-Milking',
    'nine Ladies Dancing',
    'ten Lords-a-Leaping',
    'eleven Pipers Piping',
    'twelve Drummers Drumming'
]

days = [
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

template = "On the %s day of Christmas my true love gave to me, %s\n"

def verse(day):
    day = day - 1   # Verses are 1-indexed

    gift = ", ".join(gifts[day::-1])
    day_word = days[day]

    line = template %(day_word, gift)

    if day == 0:
        line = line.replace("and ", "")    # remove extraneous and on day 1

    return line

def verses(start, end):
    return "\n".join([verse(d) for d in range(start, end+1)]) + "\n"    # Extra \n to cover join

def sing():
    return verses(1, 12)

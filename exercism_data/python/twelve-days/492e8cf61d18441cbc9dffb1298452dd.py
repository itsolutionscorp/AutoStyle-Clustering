gifts = [
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
    "twelve Drummers Drumming"
]
counts = [
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

def sing():
    return verses(1, 12)

def verse(num):
    return "On the " + counts[num - 1] + " day of Christmas my true love gave to me, " + _gift_list(num) + ".\n"

def verses(start, end):
    return "".join( verse(i) + "\n" for i in range(start, end+1) )

def _gift_list(num):
    if num == 1:
        return gifts[0]

    gift_list = ", ".join(reversed(gifts[1:num])) # exclude the first gift so we can insert the "and"
    return gift_list + ", and " + gifts[0]

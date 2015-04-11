gifts = ["nothing because it was day 0",
    "and a Partridge in a Pear Tree.",
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

ordinals = ["zeroth",
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
        "twelfth"]

def construct_gifts(place):
    if (place == 1):
        return gifts[place]
    else:
        return gifts[place] + ", " + construct_gifts(place - 1)

def verse(n):
    if n == 1:
        verse = "On the first day of Christmas my true love gave to me, a Partridge in a Pear Tree."
    else:
        head = "On the {0} day of Christmas my true love gave to me, ".format(ordinals[n])
        end = construct_gifts(n)
        verse = head + end

    return verse + "\n"


def verses (start, end):
    output = []
    for x in range(start, end + 1):
        output.append(verse(x))

        output.append("\n")
            
    return "".join(output)

def sing():
    return verses(1, 12)

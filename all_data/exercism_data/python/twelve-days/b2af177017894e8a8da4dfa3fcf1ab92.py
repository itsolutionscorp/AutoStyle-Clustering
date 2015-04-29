class Song(object):
    """Contains bits of the Twelve Days
    of Christmas song, referenced by other
    functions in the module."""

    ordinals = {
        1: "first",
        2: "second",
        3: "third",
        4: "fourth",
        5: "fifth",
        6: "sixth",
        7: "seventh",
        8: "eighth",
        9: "ninth",
        10: "tenth",
        11: "eleventh",
        12: "twelfth"
    }

    items = {
        1: "and a Partridge in a Pear Tree.\n",
        2: "two Turtle Doves",
        3: "three French Hens",
        4: "four Calling Birds",
        5: "five Gold Rings",
        6: "six Geese-a-Laying",
        7: "seven Swans-a-Swimming",
        8: "eight Maids-a-Milking",
        9: "nine Ladies Dancing",
        10: "ten Lords-a-Leaping",
        11: "eleven Pipers Piping",
        12: "twelve Drummers Drumming",
    }

    verse_skeleton = "On the {0} day of Christmas my true love gave to me, {1}"


def sing():
    return verses(1, 12)


def verse(verse_number):

    # Differentiate between having one item and having multiple items,
    # with the difference being that in a single verse, you won't have the
    # "and" in "a Partridge."
    if verse_number != 1:
        item_string = ", ".join(Song.items[i]
                                for i in range(verse_number, 0, -1))
    else:
        item_string = "a Partridge in a Pear Tree.\n"

    return Song.verse_skeleton.format(Song.ordinals[verse_number], item_string)


def verses(start_verse, end_verse):
    return "\n".join(verse(i)
                     for i in range(start_verse, end_verse + 1)) + "\n"

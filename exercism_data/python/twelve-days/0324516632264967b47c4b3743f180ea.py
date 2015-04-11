"""The Twelve Days of Christmas"""

_VERSE = "On the {} day of Christmas my true love gave to me, {}.\n"
_ORDINAL = (
    None, "first", "second", "third", "fourth", "fifth", "sixth", "seventh",
    "eighth", "ninth", "tenth", "eleventh", "twelfth"
)
_GIFT = (
    None, "a Partridge in a Pear Tree", "two Turtle Doves",
    "three French Hens", "four Calling Birds", "five Gold Rings",
    "six Geese-a-Laying", "seven Swans-a-Swimming", "eight Maids-a-Milking",
    "nine Ladies Dancing", "ten Lords-a-Leaping", "eleven Pipers Piping",
    "twelve Drummers Drumming"
)


def verse(nth):
    assert nth > 0
    if nth == 1:
        gifts = _GIFT[nth]
    else:  # nth >= 2
        gifts = ", ".join(_GIFT[nth:1:-1]) + ", and " + _GIFT[1]
    return _VERSE.format(_ORDINAL[nth], gifts)


def verses(start, end):
    return "\n".join(verse(nth) for nth in xrange(start, end + 1)) + "\n"


def sing():
    return verses(1, 12)

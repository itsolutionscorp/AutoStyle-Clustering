def sing():
    return verses(1, 12)

def verses(from_nr, to_nr):
    verses = [verse(nr) for nr in range(from_nr, to_nr + 1)] + [""]
    return "\n".join(verses)

def verse(nr):
    return "On the %s day of Christmas my true love gave to me, %s.\n" \
           % (_ordinal(nr), ", ".join(_gifts_for_verse(nr)))

_ordinals = (
    "first", "second", "third", "fourth", "fifth", "sixth",
    "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"
)

def _ordinal(nr):
    return _ordinals[nr - 1]

_gifts = (
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
)

def _gifts_for_verse(nr):
    if nr == 1:
        yield _gifts[0]
    else:
        for i in range(nr - 1, 0, -1):
            yield _gifts[i]
        yield "and " + _gifts[0] 

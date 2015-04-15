# -*- coding: utf-8 -*-

_TEMPLATE = "On the {} day of Christmas my true love gave to me, {}.\n"

_ORDINALS = ["first", "second", "third", "fourth",
             "fifth", "sixth", "seventh", "eighth",
             "ninth", "tenth", "eleventh", "twelfth"]

_GIFTS = [
    "and a Partridge in a Pear Tree",
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

def sing() -> str:
    return verses(1, 12)

def verses(m: int, n: int) -> str:
    return "\n".join(verse(i) for i in range(m, n+1)) + "\n"

def verse(n: int) -> str:
    return _TEMPLATE.format(_ordinal(n), _gifts(n))

def _ordinal(n: int) -> str:
    return _ORDINALS[n-1]

def _gifts(n: int) -> str:
    if n == 1:
        return "a Partridge in a Pear Tree"
    else:
        return ", ".join(_GIFTS[:n][::-1])

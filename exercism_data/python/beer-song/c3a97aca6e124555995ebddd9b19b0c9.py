# !/usr/bin/python


def count_bottles(n):
    if n == 0:
        return "no more bottles"
    elif n == 1:
        return "1 bottle"
    else:
        return str(n) + " bottles"


def verse(n):
    if n == 0:
        return "No more bottles of beer on the wall, no more bottles of beer.\n" \
               "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    elif n == 1:
        return "1 bottle of beer on the wall, 1 bottle of beer.\n" \
               "Take it down and pass it around, no more bottles of beer on the wall.\n"
    else:
        return count_bottles(n) + \
               " of beer on the wall, " + \
               count_bottles(n) + \
               " of beer.\n" \
               "Take one down and pass it around, " + \
               count_bottles(n - 1) + \
               " of beer on the wall.\n"


def song(start, end=0):
    return "\n".join([verse(i) for i in reversed(range(end, start + 1))]) + "\n"

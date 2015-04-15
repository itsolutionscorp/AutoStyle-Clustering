#!/usr/bin/python3

VERSES = {
    0: """No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.\n""",
    1: """1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.\n""",
    99: """{0} bottles of beer on the wall, {0} bottles of beer.
Take one down and pass it around, {1} bottle{2} of beer on the wall.\n""",
}


def verse(num):
    """
    Returns sinlge verse of 'Beer-song'
    """
    if num in (0, 1):
        return VERSES[num]
    suffix = '' if num == 2 else 's'
    return VERSES[99].format(num, num-1, suffix)


def song(start=99, end=0):
    """
    Returns whole song, or several verses from 'Beer-song'
    """
    assert start in range(0, 100) and start >= end, 'Wrong start/end values'
    all_verses = [verse(num) for num in range(start, end-1, -1)]
    return '\n'.join(all_verses) + '\n'


if __name__ == "__main__":
    print(song(end=9))
    print(song(8, 6))
    print(song(5))
    print(song(42, 42))

__author__ = 'oniwa'


def song(beg, *argv):
    sng = ''
    if argv is ():
        end = 0
    else:
        end = argv[0]
    for num in range(beg, end - 1, -1):
        sng = sng + (verse(num)) + '\n'

    return sng

def verse(num):
    if num is 0:
        return ("No more bottles of beer on the wall, no more bottles of beer.\n"
                "Go to the store and buy some more, 99 bottles of beer on the wall.\n")
    elif num is 1:
        return ("1 bottle of beer on the wall, 1 bottle of beer.\n"
                "Take it down and pass it around, no more bottles of beer on the wall.\n")
    elif num is 2:
        return ("2 bottles of beer on the wall, 2 bottles of beer.\n"
                "Take one down and pass it around, 1 bottle of beer on the wall.\n")
    else:
        return ("{0} bottles of beer on the wall, {0} bottles of beer.\n"
                "Take one down and pass it around, {1} bottles of beer on the wall.\n").format(num, num - 1)

def verse(n):
    s = ""

    if n > 1:
        s += '%d bottles of beer on the wall, %d bottles of beer.\n' % (n, n)
        s += 'Take one down and pass it around, '
        if n > 2:
            s += '%d bottles of beer on the wall.\n' % (n - 1)
        else:
            s += '1 bottle of beer on the wall.\n'
    elif n == 1:
        s += '1 bottle of beer on the wall, 1 bottle of beer.\n'
        s += 'Take it down and pass it around, '
        s += 'no more bottles of beer on the wall.\n'
    else:
        s += 'No more bottles of beer on the wall, no more bottles of beer.\n'
        s += 'Go to the store and buy some more, '
        s += '99 bottles of beer on the wall.\n'

    return s

def song(start, end=0):
    s = ''
    v = start

    while v >= end:
        s += verse(v) + '\n'
        v -= 1

    return s

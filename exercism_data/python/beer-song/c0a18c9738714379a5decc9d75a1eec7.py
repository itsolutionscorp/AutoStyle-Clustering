def bottle(n):
    if n == -1:
        return "99 bottles"
    elif n == 0:
        return "no more bottles"
    elif n == 1:
        return "1 bottle"
    else:
        return "%d bottles" % n

def verse(n):
    before = bottle(n)
    after = bottle(n - 1)

    x = "{before} of beer on the wall, {before} of beer.\n".format(before=before)

    if n == 0:
        y = "go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else:
        what = "it" if n == 1 else "one"
        y = "take {what} down and pass it around, {after} of beer on the wall.\n".format(what=what, after=after)

    return "".join(map(str.capitalize, (x, y)))


def song(m, n=0):
    return ''.join(verse(i) + "\n" for i in range(m, n - 1, -1))

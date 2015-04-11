def verse(n):
    if n < 0:
        raise ValueError("There's no such thing as a negative bottle of beer.")
    elif n == 0:
        return """No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.\n"""
    elif n == 1:
        return """1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.\n"""
    elif n == 2:
        return """2 bottles of beer on the wall, 2 bottles of beer.
Take one down and pass it around, 1 bottle of beer on the wall.\n"""
    else:
        return """{0} bottles of beer on the wall, {0} bottles of beer.
Take one down and pass it around, {1} bottles of beer on the wall.\n""".format(n, n - 1)


def song(start_verse, end_verse=None):
    if end_verse:
        return "\n".join([verse(i) for i in range(start_verse, end_verse - 1, -1)]) + "\n"
    else:
        return "\n".join([verse(i) for i in range(start_verse, -1, -1)]) + "\n"

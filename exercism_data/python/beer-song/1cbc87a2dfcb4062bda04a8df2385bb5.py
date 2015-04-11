
def pluralize_bottle(n):
    if n == 1:
        return "1 bottle"
    elif n == 0:
        return "no more bottles"
    return "{} bottles".format(n)

def verse(n):
    first_line = str.format("{0} of beer on the wall, {0} of beer.\n",
                            pluralize_bottle(n)).capitalize()
    if n == 0:
        second_line = "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else:
        second_line = str.format("Take {} down and pass it around, {} of beer on the wall.\n",
                                 "it" if n == 1 else "one",
                                 pluralize_bottle(n-1))
    return first_line + second_line

def song(start, end=None):
    return ''.join(verse(n) + '\n'
                   for n in range(start, (end or 0) - 1, -1))


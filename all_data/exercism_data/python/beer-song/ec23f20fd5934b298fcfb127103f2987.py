def song(n, m=0):
    return "\n".join(verse(x) for x in range(n, m - 1, -1)) + "\n"

def bottle(n):
    if n > 1:
        return "{} bottles".format(n)
    else:
        return "1 bottle" if n == 1 else "no more bottles"

def make_lines(n):
    return {
        "first": "{} of beer on the wall, {} of beer.".format(
            bottle(n).capitalize(), bottle(n)),
        "second": ("Take one down and pass it around, "
                   "{} of beer on the wall.").format(bottle(n-1))
        }

def verse(n):
    lines = make_lines(n)
    special = {0: ("Go to the store and buy some more, "
                   "99 bottles of beer on the wall."),
               1: ("Take it down and pass it around, "
                   "no more bottles of beer on the wall.")}
    if n < 2:
        lines["second"] = special[n]
    return "{first}\n{second}\n".format(**lines)

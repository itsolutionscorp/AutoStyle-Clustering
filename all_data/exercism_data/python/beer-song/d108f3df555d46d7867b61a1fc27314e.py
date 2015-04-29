def song(start, end=0):
    return ''.join(verse(n) + "\n" for n in range(start, end-1, -1))

def verse(n):

    plural = lambda n: 's' if n!=1 else ''
    pred   = lambda n: "one" if n > 1 else "it"
    nomore = lambda s: 'No more' if s else 'no more'
    num    = lambda n, s: str(n) if n>0 else nomore(s)
    beer   = lambda n, s: num(n, s) + " bottle" + plural(n) + " of beer"
    wall   = lambda n, s: beer(n, s) + " on the wall"

    takeline = lambda n: "Take " + pred(n) + " down and pass it around, " + wall(n-1, False) + ".\n"
    buyline  = lambda : "Go to the store and buy some more, " + wall(99, False) + ".\n"

    first_line  = lambda n: wall(n, True) + ", " + beer(n, False) + ".\n"
    second_line = lambda n: takeline(n) if n >= 1 else buyline()

    return first_line(n) + second_line(n)

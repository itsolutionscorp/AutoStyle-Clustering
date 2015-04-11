SONG_TEMPLATE = """{n} bottle{plural} of beer on the wall, {n} bottle{plural} of beer.
Take {itone} down and pass it around, {less} bottle{plural_less} of beer on the wall.
"""

SONG_ZERO = """No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
"""

def verse(n):
    """return the verse of the beer song that strarts with "nbottles of beer..."
    """
    bottles, lessbottles = n, n-1
    esses = lambda n: "s" if (n > 1 or n == 0) else ""
    if bottles == 0:
        return SONG_ZERO
    elif lessbottles == 0:
        p_l = esses(lessbottles)
        lessbottles = "no more"
    else:
        p_l = esses(lessbottles)
    return SONG_TEMPLATE.format(n=bottles,
                                less=lessbottles,
                                plural=esses(bottles),
                                plural_less=p_l,
                                itone= "it" if bottles == 1 else "one")


def song(start=99,end=0):
    """Returns (part of) the beer song."""
    return "\n".join(verse(n) for n in range(start, end-1, -1))+"\n"

"""99 Bottles of Beer on the Wall."""


def plural_suffix(num):
    """Return 's' for plural (or zero) and '' for one."""
    return "s" if num > 1 or num == 0 else ""


def bottle(num):
    """(``num``|no more) bottle(s)

    :param num: the number of bottles.
    """
    return "{} bottle{}".format(
        num if num > 0 else "no more",
        plural_suffix(num))


def bottle_of_beer(num):
    """(``num``|no more) bottle(s) of beer

    :param num: the number of bottles.
    """
    return "{} of beer".format(bottle(num))


def bottle_of_beer_on_the_wall(num):
    """(``num``|no more) bottle(s) of beer on the wall

    :param num: the number of bottles.
    """
    return "{} on the wall".format(bottle_of_beer(num))


def verse(num):
    """
    (``num``|No more) bottle(s) of beer on the wall, (``num``|no more) bottle(s) of beer.
    (Take (it|one) down and pass it around, (``num - 1``|no more) bottle(s) of beer on the wall.|
     Go to the store and buy some more, 99 bottles of beer on the wall.)

    :param num: the number of bottles.
    """
    lines = [None] * 2
    lines[0] = "{}, {}.".format(
        bottle_of_beer_on_the_wall(num).capitalize(),
        bottle_of_beer(num))
    if num >= 1:
        lines[1] = "Take {} down and pass it around, {}.".format(
            "it" if num == 1 else "one",
            bottle_of_beer_on_the_wall(num - 1))
    else:
        lines[1] = "Go to the store and buy some more, {}.".format(
            bottle_of_beer_on_the_wall(99))
    return "\n".join(lines) + "\n"


def sing(start, stop=0):
    """Return "(``start`` .. ``stop``) Bottles of Beer on the Wall"."""
    verses = (verse(n) for n in xrange(start, stop - 1, -1))
    return "\n".join(verses) + "\n"


class Beer(object):
    """99 Bottles of Beer on the Wall."""

    def verse(self, num):
        """A wrapper for :func:`beer.verse`."""
        return verse(num)

    def sing(self, start, stop=0):
        """A wrapper for :func:`beer.sing`."""
        return sing(start, stop)

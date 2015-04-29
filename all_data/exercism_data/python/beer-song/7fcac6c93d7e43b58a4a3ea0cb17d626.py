class Beer(object):
    """A class for the beer song"""

    @classmethod
    def verse(cls, n):
        """Returns the beer song verse, where there are n bottles of beer on
        the wall at the beginning of the verse.
        """
        now_bottles = cls.__bottles(n)
        then_bottles = cls.__bottles((n-1) % 100)
        go_or_take = cls.__go_or_take(n)
        return ("{0} on the wall, {1}.\n"
                "{2}, {3} on the wall.\n").format(
                    now_bottles[0].upper() + now_bottles[1:],
                    now_bottles,
                    go_or_take,
                    then_bottles)

    @staticmethod
    def __bottles(n):
        if n == 0:
            return "no more bottles of beer"
        elif n == 1:
            return "1 bottle of beer"
        return "{} bottles of beer".format(n)

    @staticmethod
    def __go_or_take(n):
        if n == 0:
            return "Go to the store and buy some more"
        elif n == 1:
            return "Take it down and pass it around"
        return "Take one down and pass it around"

    @classmethod
    def sing(cls, start, end=0):
        """The verses from start to end.
        Each with one bottle less than the one before it.
        """
        return ''.join([(cls.verse(n) + '\n')
                        for n in range(start, end-1, -1)])

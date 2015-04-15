class Beer(object):

    _first_part = '{0} bottle{1} of beer on the wall, {2} bottle{1} of beer.\n'
    _second_part = 'Take one down and pass it around, {0} bottle{1} of beer on the wall.\n'

    @staticmethod
    def plural(n):
        if n != 1:
            return 's'
        return ''

    @staticmethod
    def numeral(n):
        if n == 0:
            return 'no more'
        return str(n)

    @classmethod
    def verse(cls, n):
        return cls.first_part(n) + cls.second_part(n-1)

    @classmethod
    def first_part(cls, n):
        ns = cls.numeral(n)
        return cls._first_part.format(ns.capitalize(), cls.plural(n), ns)

    @classmethod
    def second_part(cls, n):
        if n == 0:
            return "Take it down and pass it around, no more bottles of beer on the wall.\n"
        elif n == -1:
            return "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
        return cls._second_part.format(cls.numeral(n), cls.plural(n))

    @classmethod
    def sing(cls, first, last=0):
        return "".join(cls.verse(i) + "\n" for i in range(first, last-1, -1))

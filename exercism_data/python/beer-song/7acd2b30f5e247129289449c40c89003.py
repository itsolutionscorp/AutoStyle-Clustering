class Beer(object):

    _first_part = '{0} bottle{1} of beer on the wall, {2} bottle{1} of beer.\n'
    _second_part =   "Take one down and pass it around, {0} bottle{1} of beer on the wall.\n"


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


    @staticmethod
    def verse(n):
        return Beer.first_part(n) + Beer.second_part(n-1)

    @staticmethod
    def first_part(n):
        ns = Beer.numeral(n)
        return Beer._first_part.format(ns.capitalize(), Beer.plural(n), ns)

    @staticmethod
    def second_part(n):
        if n == 0:
            return "Take it down and pass it around, no more bottles of beer on the wall.\n"
        elif n == -1:
            return "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
        return Beer._second_part.format(Beer.numeral(n), Beer.plural(n))

    @staticmethod
    def sing(first, last = 0):
        acc = ""
        for i in range(first, last-1, -1):
            acc += Beer.verse(i) + "\n"
        return acc

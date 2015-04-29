import string

def zero():
    return ("No more bottles of beer on the wall, "
            "no more bottles of beer.\n"
            "Go to the store and buy some more, "
            "99 bottles of beer on the wall.\n")

def one():
    return ("1 bottle of beer on the wall, "
            "1 bottle of beer.\n"
            "Take it down and pass it around, "
            "no more bottles of beer on the wall.\n")

def two():
    return ("2 bottles of beer on the wall, "
            "2 bottles of beer.\n"
            "Take one down and pass it around, "
            "1 bottle of beer on the wall.\n")

def nth(n):
    if n == 0:
        return zero()
    elif n == 1:
        return one()
    elif n == 2:
        return two()
    else:
        return ("{0} bottles of beer on the wall, "
                "{0} bottles of beer.\n"
                "Take one down and pass it around, "
                "{1} bottles of beer on the wall.\n".format(n, n - 1))

class Beer(object):

    def verse(self, number):
        return nth(number);

    def verses(self, start, end = 0):
        return [self.verse(i) for i in range(start, end - 1, -1)]

    def sing(self, start, end = 0):
        return string.join(self.verses(start, end), "\n") + "\n"

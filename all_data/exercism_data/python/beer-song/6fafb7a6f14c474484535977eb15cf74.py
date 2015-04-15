class Beer:
    def verse(self, n):
        return '''{0} of beer on the wall, {1} of beer.
{2} {3} of beer on the wall.
'''.format(self.bottles(n).capitalize(),
           self.bottles(n),
           self.bridge(n),
           self.bottles(n-1))

    def sing(self, a, b=0):
        s = ""
        for i in reversed(range(b, a+1)):
            s += self.verse(i) + "\n"
        return s

    def bottles(self, n):
        if   n == -1: return "99 bottles"
        elif n ==  0: return "no more bottles"
        elif n ==  1: return "1 bottle"
        else:         return "{0} bottles".format(n)

    def bridge(self, n):
        if   n == 0:  return "Go to the store and buy some more,"
        elif n == 1:  return "Take it down and pass it around,"
        else:         return "Take one down and pass it around,"

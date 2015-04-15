class Bottle:
    def __init__(self,i):
        self.i = i if i >=0 else 99
        self.identifier = 'one' if i > 1 else 'it'

    def __str__(self):
        return "%s bottle%s of beer" % (
            self.i if self.i > 0 else 'no more',
            's' if self.i != 1 else ''
        )

    def first_line(self):
        line = "%s on the wall, %s." % ( self, self )
        return line.capitalize()

    def second_line(self):
        line =  "Take " + self.identifier + " down and pass it around, " if self.i > 0 else 'Go to the store and buy some more, ';

        one_less = Bottle( self.i - 1 )

        return line + one_less.__str__() + " on the wall.";

    def verse(self):
        return "\n".join([ self.first_line(), self.second_line() ]) + "\n"



class Beer:

    def verse(self,n):
        return Bottle(n).verse()

    def sing(self,n,m=0):
        return "\n".join([ self.verse(i) for i in reversed(range(m,n+1)) ]) + "\n"

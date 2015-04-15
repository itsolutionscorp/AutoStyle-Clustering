class Beer:

    def __init__(self):
        self.line=("{0} of beer on the wall, {1} of beer.\n" +
        "{2}, {3} of beer on the wall.\n")

    def verse(self,n):
        return self.line.format( *self._get_options(n) )
    
    def sing(self,start,finish=0):
        return "\n".join([self.verse(n) for n in range(start,finish-1,-1)])+"\n"
    
    def _get_options(self,n):
        action=""
        if n>0:
            action="Take {0} down and pass it around".format(
            "it" if n == 1 else "one")
        else:
            action="Go to the store and buy some more"
        before=self._get_beers(n)
        after=self._get_beers(n-1)
        return ["N" + before[1:] if n == 0 else before, before, action, after]


    def _get_beers(self,n):
        if n==0:
            return "no more bottles"
    
        elif n<0:
            return self._get_beers(99)
        else:
            return "{0} bottle{1}".format(n,'s' if n>1 else '')


def main():
    beer=Beer()
    print(beer.sing(8,3))


if __name__=="__main__":
    main()

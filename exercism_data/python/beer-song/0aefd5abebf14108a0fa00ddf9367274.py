class Beer(object):

    def sing(self, *args):
        verses = []
        (start, end) = (args[0], -1)

        if len(args) > 1:
            end = end + args[1]

        for i in range(args[0],end,-1):
            verses.append(self.verse(i))

        return "\n".join(verses) + "\n"


    def verse(self, n):
        bottle_n = self._plural(n)
        bottle_less = self._plural(n-1)
        if n >= 1:
            return  ("%s of beer on the wall, %s of beer.\n"
                     "Take %s down and pass it around, %s of beer on the wall.\n" %(
                                            bottle_n,
                                            bottle_n,
                                            self._it_or_one(n),
                                            bottle_less)
                                        ) 
        else:
            return ("No more bottles of beer on the wall, no more bottles of beer.\n"
                    "Go to the store and buy some more, 99 bottles of beer on the wall.\n")

    def _it_or_one(self,n):
        if n > 1:
            return "one"
        return "it"

    def _plural(self,n):
        if n > 1:
            return "%d bottles" %(n)
        if n == 1:
            return "1 bottle"
        return 'no more bottles'

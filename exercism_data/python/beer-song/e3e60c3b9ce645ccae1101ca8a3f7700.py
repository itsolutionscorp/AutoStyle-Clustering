class Beer:
    def verse(self, n):
        nbob=self.bob(n)
        kbob=self.bob((n-1)%100)
        wall=" on the wall"
        return "%c%s%s, %s.\n%s, %s%s.\n" \
            % (nbob[0].upper(), nbob[1:], wall, nbob,\
               ["Go to the store and buy some more", \
                    "Take %s down and pass it around"\
                    %["it", "one"][n!=1]][bool(n)], kbob, wall)

    def bob(self, n):
        return "%s bottle%s of beer" \
            % (["no more", str(n)][bool(n)],["", "s"][n!=1])

    def sing(self, n, k=0):
        if n<k:
            return ""
        return self.verse(n)+"\n"+self.sing(n-1, k)

VERSE_A = "{s} {b} of beer on the wall, {s} {b} of beer.\nTake {a} down and pass it around, "
VERSE_B = '{e} {b} of beer on the wall.\n'
VERSE_C = "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
BOTTLES = ("bottle", "bottles")

class Beer(object):
    def verse(self, count):
        if count == 0:
            return VERSE_C
        s = count
        e = count -1
        
        if s > 1:
            a = "one"
            b = BOTTLES[1]
        if s == 1:
            a = "it"
            b = BOTTLES[0]
        if e == 0:
            e = "no more"

        if e == 0  or e > 1:
            b2 = BOTTLES[1]
        if e == 1:
            b2 = BOTTLES[0]

            
        return "".join([VERSE_A.format(s=s, a=a, b=b), VERSE_B.format(e=e, b=b2)])

    def sing(self, s, e=None):
        if e:
            e = e-1
        else:
            e = -1
        return "\n".join([self.verse(x) for x in xrange(s, e, -1)]) + "\n"

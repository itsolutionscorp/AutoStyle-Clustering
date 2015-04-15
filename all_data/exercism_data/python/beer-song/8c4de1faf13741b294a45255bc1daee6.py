class Beer:
    def verse(self, i):
        base = "%s of beer on the wall, %s of beer.\n"
        some = "Take %s down and pass it around, %s of beer on the wall.\n"
        none = "Go to the store and buy some more, %s of beer on the wall.\n"

        if i == 0:
            numbers = ("no more bottles", "99 bottles")
        elif i == 1:
            numbers = ("1 bottle", "it", "no more bottles")
        elif i == 2:
            numbers = ("2 bottles", "one", "1 bottle")
        else:
            numbers = ("%d bottles" % i, "one", "%d bottles" % (i-1))

        if i == 0:
            return base % (numbers[0].capitalize(), numbers[0]) + none % numbers[1]
        else:
            return base % (numbers[0], numbers[0]) + some % numbers[1:]


    def sing(self, s, e=0):
        return '\n'.join(self.verse(i) for i in xrange(s, e-1, -1)) + '\n'

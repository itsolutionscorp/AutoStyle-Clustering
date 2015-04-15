def pluralize(n):
    if n==0:
        return "no more bottles"
    if n==1:
        return "1 bottle"
    return "%d bottles" % n

def take_down(n):
    if n==0:
        return "Go to the store and buy some more"
    if n==1:
        one = "it"
    else:
        one = "one"
    return "Take %s down and pass it around" % one

class Beer(object):
    def verse(self, n):
        if n==0:
            next_n = 99
        else:
            next_n = n-1

        bottles = pluralize(n)

        words = {
            "beer": "of beer on the wall",
            "bottles": bottles,
            "next_bottles": pluralize(next_n),
            "cap_bottles": bottles.capitalize(),
            "take_down": take_down(n)
        }

        line1 = "%(cap_bottles)s %(beer)s, %(bottles)s of beer.\n" % words
        line2 = "%(take_down)s, %(next_bottles)s %(beer)s.\n" % words

        return line1 + line2

    def sing(self, hi, lo=0):
        return "".join(self.verse(n) + "\n" for n in range(hi, lo-1, -1))

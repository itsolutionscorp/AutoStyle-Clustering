def pluralize(n):
    if n == 0:
        return "no more bottles"
    if n == 1:
        return "1 bottle"
    return "%d bottles" % n


def take_down(n):
    if n == 0:
        return "Go to the store and buy some more"
    if n == 1:
        one = "it"
    else:
        one = "one"
    return "Take %s down and pass it around" % one


class Beer(object):
    def verse(self, n):
        if n == 0:
            next_bottles = pluralize(99)
        else:
            next_bottles = pluralize(n-1)

        bottles = pluralize(n)

        fmt1 = "{cap_bottles} of beer on the wall, {bottles} of beer.\n"
        line1 = fmt1.format(cap_bottles=bottles.capitalize(), bottles=bottles)
        fmt2 = "{take_down}, {next_bottles} of beer on the wall.\n"
        line2 = fmt2.format(take_down=take_down(n), next_bottles=next_bottles)

        return line1 + line2

    def sing(self, hi, lo=0):
        return "".join(self.verse(n) + "\n" for n in range(hi, lo-1, -1))

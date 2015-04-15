class Beer(object):
    def verse(self, n):
        return (self.first_line(n).capitalize() +
                self.second_line(n).capitalize())

    def sing(self, start, stop=0):
        return "\n".join(map(self.verse,
                             reversed(range(stop, start+1)))) + "\n"

    def pluralize_bottles(self, num):
        if num == 0:
            return "no more bottles"
        elif num == 1:
            return "1 bottle"
        else:
            return "{n} bottles".format(n=num)

    def pronoun(self, n):
        if n == 1:
            return "it"
        else:
            return "one"

    def first_line(self, n):
        return ("{b} of beer on the wall, {b} of beer.\n"
                .format(b=self.pluralize_bottles(n)))

    def second_line(self, n):
        if n == 0:
            return ("Go to the store and buy some more, " +
                    "99 bottles of beer on the wall.\n")
        else:
            return (self.second_line_first_part(n) +
                    self.second_line_second_part(n))

    def second_line_first_part(self, n):
        return ("Take {p} down and pass it around, "
                .format(p=self.pronoun(n)))

    def second_line_second_part(self, n):
        return ("{b} of beer on the wall.\n"
                .format(b=self.pluralize_bottles(n-1)))

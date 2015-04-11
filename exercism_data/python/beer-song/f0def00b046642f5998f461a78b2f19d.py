class Beer:
    def _bottle_str(self, number):
        if number == 0:
            return "no more bottles"
        if number == 1:
            return "1 bottle"
        else:
            return "%s bottles" % number

    def _one_it_str(self, number):
        if number == 1:
            return "it"
        else:
            return "one"

    def _last_verse(self, number):
        if number == 0:
            return "Go to the store and buy some more, " \
                   "99 bottles of beer on the wall.\n"
        else:
            return "Take %s down and pass it around, %s of " \
                   "beer on the wall.\n" % (self._one_it_str(number),
                                            self._bottle_str(number - 1))

    def verse(self, number):
        r_string = ["%s of beer on the wall, "
                    % self._bottle_str(number).capitalize(),
                    "%s of beer.\n" % self._bottle_str(number)]

        return ''.join(r_string) + self._last_verse(number)

    def sing(self, start, finish=0):

        re_str = ''

        for number in reversed(range(finish, start + 1)):
            re_str += self.verse(number) + '\n'

        return re_str

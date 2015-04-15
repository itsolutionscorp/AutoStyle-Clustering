from __future__ import unicode_literals

class Beer(object):
    """ Generates the verses to 99 bottles of beer on the wall """
    @classmethod
    def _beer_word(cls, num):
        if num == 0:
            return "No more"
        else:
            return str(num)

    @classmethod
    def _bottle_word(cls, num):
        if num == 1:
            return "bottle"
        else:
            return "bottles"

    @classmethod
    def _instruction(cls, num):
        if num == 1:
            return "Take it down and pass it around"
        elif num == 0:
            return "Go to the store and buy some more"
        else:
            return "Take one down and pass it around"

    @classmethod
    def verse(self, num):
        next_num = num - 1
        if next_num < 0:
            next_num = 99

        return ("{0} {1} of beer on the wall, {2} {1} of beer.\n"
                "{3}, {4} {5} of beer on the wall.\n".format(
                    self._beer_word(num), self._bottle_word(num),
                    self._beer_word(num).lower(),
                    self._instruction(num),
                    self._beer_word(next_num).lower(),
                    self._bottle_word(next_num)))

    def sing(self, begin, end=None):
        if end is None:
            end = 0
        end -= 1

        return ''.join(self.verse(i) + '\n' for i in xrange(begin, end, -1))

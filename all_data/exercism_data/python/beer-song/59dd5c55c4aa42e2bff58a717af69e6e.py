class Beer(object):
    PART1 = "{bottles} of beer on the wall, {bottles} of beer.\n"
    PART2 = "Take {} down and pass it around, {} of beer on the wall.\n"

    def _bottles(self, num):
        return "{} bottle{}".format(num if num > 0 else 'no more',
                                    '' if num == 1 else 's')

    def verse(self, num):
        PART1 = self.PART1.format(bottles=self._bottles(num)).capitalize()

        if num > 0:
            PART2 = self.PART2.format('it' if num == 1 else 'one',
                                      self._bottles(num - 1))
        elif num == 0:
            PART2 = ("Go to the store and buy some more, "
                     "99 bottles of beer on the wall.\n")

        return ''.join([PART1, PART2])

    def sing(self, start, end=0):
        return '\n'.join(self.verse(i) for i in range(start, end-1, -1)) + '\n'

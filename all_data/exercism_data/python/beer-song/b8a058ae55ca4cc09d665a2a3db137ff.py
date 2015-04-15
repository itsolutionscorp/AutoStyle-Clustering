class Beer(object):
    part1 = "{bottles} of beer on the wall, {bottles} of beer.\n"
    part2 = "Take {} down and pass it around, {} of beer on the wall.\n"

    def _bottles(self, num):
        if num > 1:
            return '{} bottles'.format(num)
        elif num == 1:
            return '1 bottle'
        elif num == 0:
            return 'no more bottles'

    def _one_or_it(self, num):
        if num > 1:
            return 'one'
        elif num == 1:
            return 'it'

    def verse(self, num):
        part1 = self.part1.format(bottles=self._bottles(num)).capitalize()

        if num > 0:
            part2 = self.part2.format(self._one_or_it(num),
                                      self._bottles(num - 1))
        if num == 0:
            part2 = ("Go to the store and buy some more, "
                     "99 bottles of beer on the wall.\n")

        return ''.join([part1, part2])

    def sing(self, start, end=0):
        return '\n'.join(self.verse(i) for i in range(start, end-1, -1)) + '\n'

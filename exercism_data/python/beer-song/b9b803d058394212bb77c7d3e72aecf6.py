def _bottles(bottle_num):
    return 'bottle' if bottle_num == 1 else 'bottles'

class Beer(object):

    generic_begin = '{} {} of beer on the wall, {} {} of beer.\n'
    generic_end = 'Take one down and pass it around, {} {} of beer on the wall.\n'
    one_end = 'Take it down and pass it around, no more bottles of beer on the wall.\n'
    zero_begin = 'No more bottles of beer on the wall, no more bottles of beer.\n'
    zero_end = 'Go to the store and buy some more, 99 bottles of beer on the wall.\n'


    def verse(self, bottle_num):
        if bottle_num > 0:
            begin = self.generic_begin
            if bottle_num == 1:
                end = self.one_end
            else:
                end = self.generic_end
            return (begin.format(bottle_num, _bottles(bottle_num),
                                 bottle_num, _bottles(bottle_num)) +
                    end.format(bottle_num-1, _bottles(bottle_num-1)))
        else:
            return self.zero_begin + self.zero_end

    def sing(self, end, start=0):
        return ''.join([(self.verse(i) + '\n') for i in xrange(end, start-1, -1)])

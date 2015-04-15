class Beer(object):

    def __init__(self):
        pass

    def verse(self, bottles):
        
        if bottles == 1:
            self.current = '1 bottle of beer'
            self.action = 'Take it down and pass it around'
            self.next = 'no more bottles of beer'
        elif bottles == 0:
            self.current = 'no more bottles of beer'
            self.action = 'Go to the store and buy some more'
            self.next = '99 bottles of beer'
        else:
            self.current = str(bottles) + ' bottles of beer'
            self.action = 'Take one down and pass it around'
            if bottles == 2:
                self.next = str(bottles - 1) + ' bottle of beer'
            else:
                self.next = str(bottles - 1) + ' bottles of beer'

        self.line1 = '{0} on the wall, {0}.\n'.format(self.current).capitalize()
        self.line2 = '{0}, {1} on the wall.\n'.format(self.action, self.next).capitalize()

        return self.line1 + self.line2
    

    def sing(self, verse_start, verse_end = 0):

        self.verse_start = verse_start
        self.verse_end = verse_end

        song = '\n'.join(self.verse(x) for x in range(self.verse_end, self.verse_start + 1)[::-1]) + '\n'
        return song


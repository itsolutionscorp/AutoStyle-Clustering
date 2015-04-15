class Beer(object):

    def __init__(self):
        pass

    def beer_count(self, bottles):

        self.bottles = bottles
        fragment = str(self.bottles) + ' bottles of beer on the wall, '
        if self.bottles == 1:
            fragment = fragment.replace('s', '')
        elif self.bottles == 0:
            fragment = fragment.replace('0', 'no more')

        return fragment

    def verse(self, bottles):
        
        self.bottles_crnt = bottles
        self.bottles_next = bottles - 1

        if self.bottles_crnt == 0:
            self.bottles_next = '99'

        bottle_action = 'Take one down and pass it around, '
        
        if self.bottles_crnt == 1:
            bottle_action = bottle_action.replace('one', 'it')
        elif self.bottles_crnt == 0:
            bottle_action = 'Go to the store and buy some more, '

        self.line1 = (self.beer_count(self.bottles_crnt)*2).capitalize()[:-14] + '.\n'
        self.line2 = bottle_action + self.beer_count(self.bottles_next)[:-2] + '.\n'

        return self.line1 + self.line2
    

    def sing(self, verse_start, verse_end = 0):

        self.verse_start = verse_start
        self.verse_end = verse_end

        song = '\n'.join(self.verse(x) for x in range(self.verse_end, self.verse_start +1)[::-1]) + '\n'
        return song


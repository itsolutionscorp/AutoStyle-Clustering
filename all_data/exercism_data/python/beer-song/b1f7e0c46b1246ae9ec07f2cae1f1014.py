class Beer(object):
    def base_verse(self, count):
        plurality = 's' if count != 1 else ''
        beers     = count if count != 0 else 'No more'
        return "{0} bottle{1} of beer".format(beers, plurality)
        
    def status_update(self, count):
        base = self.base_verse(count)
        return "{} on the wall, {}.\n".format(base, base.lower())
    
    def next_action(self, count):
        action = self.action(count)
        base   = self.base_verse(self.next_beer(count))
        return "{}, {} on the wall.\n".format(action, base.lower())
    
    def action(self, count):
        if count != 0:
            if count == 1:
                return "Take it down and pass it around"
            return "Take one down and pass it around"
        else:
            return "Go to the store and buy some more"
    
    def next_beer(self, count):
        return count - 1 if count != 0 else 99     
            
    def verse(self, count):
        first_line = self.status_update(count)
        second_line = self.next_action(count)
        
        return first_line + second_line
    
    def sing(self, start, end = 0):
        song = ''
        for i in reversed(range(end, start + 1)):
            song += self.verse(i) + "\n"
        return song

class Beer(object):
    def verse(self, bottles):
        '''Return the verse specified by the number of bottles at the start.'''
        
        bottles_str = self._bottles_description(bottles)
        next_bottles_str = self._bottles_description(self._next_bottle(bottles))
        action_str = self._action_description(bottles)
        wall_str = " on the wall"
        verse = ("%s%s, %s.\n%s, %s%s.\n" % (bottles_str.capitalize(), wall_str, bottles_str,
                                             action_str, next_bottles_str, wall_str))
        return verse

    def sing(self, starting_verse, ending_verse=0):
        '''Return all verses in the specified range.'''
        
        verses = xrange(starting_verse, ending_verse-1, -1)
        return "\n".join([self.verse(bottles) for bottles in verses]) + "\n"
        

    def _bottles_description(self, bottles):
        if bottles == 1:
            count = "1 bottle"
        elif bottles == 0:
            count = "no more bottles"
        else:
            count = "%d bottles" % bottles

        return count + " of beer"

    def _action_description(self, bottles):
        if bottles == 0:
            return "Go to the store and buy some more"
        else:
            return "Take %s down and pass it around" % ("one" if bottles != 1 else "it")

    def _next_bottle(self, bottles):
        return bottles - 1 if bottles > 0 else 99

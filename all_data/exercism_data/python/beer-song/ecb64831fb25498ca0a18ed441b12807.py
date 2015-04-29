#!/usr/bin/env python

class Beer:
    def verse(self, nr):
        def bottle_phrase(amount):
            phrase = "bottle"
            if amount == 0:
                phrase = "no more " + phrase
            else:
                phrase = "{0} {1}".format(amount, phrase)
            if amount != 1:
                phrase += "s"
            return phrase
        
        def beer_phrase(amount):
            return "{0} of beer".format(bottle_phrase(amount))
        
        def wall_phrase(amount):
            return "{0} on the wall".format(beer_phrase(amount))
        
        def action_phrase(amount):
            if amount == 0:
                return "Go to the store and buy some more"
            else:
                phrase = "Take {0} down and pass it around"
                if amount == 1:
                    return phrase.format("it")
                else:
                    return phrase.format("one")
        
        result = "{0}, {1}.\n{2}, {3}.\n".format(wall_phrase(nr),
                                                 beer_phrase(nr),
                                                 action_phrase(nr),
                                                 wall_phrase((nr + 99) % 100))
        return result[0].upper() + result[1:]
    
    def sing(self, start, stop=0):
        return "\n".join([self.verse(n) for n in range(start, stop - 1, -1)]) + "\n"

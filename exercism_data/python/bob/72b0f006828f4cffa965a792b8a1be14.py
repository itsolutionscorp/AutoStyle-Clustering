import re
class Bob:

    responses = [['^\s*$',          'Fine, be that way.'],
                 ['^[A-Z0-9 \W]+$', 'Woah, chill out!'],
                 ['\\?$',           'Sure.'],
                 ['.*',             'Whatever.']]

    def hey(self, phrase):
        return [a for (r, a) in self.responses if re.search(r, phrase)][0]

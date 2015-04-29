import re

wordspec = re.compile('[a-zA-Z0-9]+')

# The REAL program:
def wordcount(s):
    d = {}
    for m in wordspec.finditer(s):
        w = m.group().lower()
        try:
            d[w] += 1
        except KeyError:
            d[w] = 1
    return d

# exercism's ever-so-CLASSy interface to what is really just a function: 
# (Seriously, these guys have been drinking waaay too much java.)
class Phrase:
    def __init__(self, s):
        self.s = s
    def word_count(self):
        return wordcount(self.s)

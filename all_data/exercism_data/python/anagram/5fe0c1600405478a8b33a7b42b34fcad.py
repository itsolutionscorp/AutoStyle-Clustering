# The REAL program (just a function):
def check(word, candidates):
    # They WOULD alternate between uppercase and lowercase, wouldn't they?
    word = word.lower()
    def is_anagram(c):
        c = c.lower()
        if c == word:
            # Really? A word isn't an anagram of itself? Lame. I object 
            # strenuously to exercism's definition of anagram.
            return False
        unused = list(word)
        for i in range(len(c)):
            # Look for the current letter of the candidate in the unused 
            # letters of the base word.  If it is there, take it out of the 
            # list, and move onto the next letter. If it is not, then the 
            # candidate is not an anagram.
            try:
                j = unused.index(c[i])
            except ValueError:
                return False
            unused.pop(j)
        # Return true iff there are no unused letters remaining.
        return unused == []
    return list(filter(is_anagram, candidates))

# exercism's highly classified interface. Seriously, not everything is a 
# fucking class!!
class Anagram:
    def __init__(self, w):
        self.word = w
    def match(self, candidates):
        return check(self.word, candidates)
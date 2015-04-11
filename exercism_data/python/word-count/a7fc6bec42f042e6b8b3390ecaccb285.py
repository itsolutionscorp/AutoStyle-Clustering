import re, operator

class Phrase:
    def __init__(self, s):
        self.s=s.lower()
    def word_count(self):
        car=(lambda x:x[0])
        cdr=(lambda x:x[1:])
        #yes, I would rather program haskell, but ghc is not installed here
        l=filter(len, sorted(re.split("\W+", self.s)))
        return dict(reduce(lambda xs, y:  [y]+xs if not xs or car(xs)[0]!=y[0] else [(y[0], car(xs)[1]+y[1])]+cdr(xs), 
                           map(lambda x:(x, 1), l), []))

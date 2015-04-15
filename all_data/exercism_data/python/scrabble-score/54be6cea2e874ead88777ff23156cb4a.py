class Word(object):
    _scores = [ (" \t\n"     , 0),
                ("AEIOULNRST", 1),
                ("DG"        , 2),
                ("BCMP"      , 3),
                ("FHVWY"     , 4),
                ("K"         , 5),
                ("JX"        , 8),
                ("QZ"        , 10)]
    scores = { l: n for ll, n in _scores for l in ll }

    def __init__(self, s):
        self.word = s

    def score(self):
        return sum(self.scores[c.upper()] for c in self.word)

class Scores:
    by_score = {
        1: "AEIOULNRST",
        2: "DG",
        3: "BCMP",
        4: "FHVWY",
        5: "K",
        8: "JX",
        10: "QZ",
    }

    by_letter = {}

for key, values in Scores.by_score.iteritems():
    for value in values:
        Scores.by_letter[value.upper()] = key


class Word:
    def __init__(self, word):
        self.word = word.strip().upper()
        self._score = sum([ Scores.by_letter[c] for c in self.word ])

    def score(self):
        return self._score

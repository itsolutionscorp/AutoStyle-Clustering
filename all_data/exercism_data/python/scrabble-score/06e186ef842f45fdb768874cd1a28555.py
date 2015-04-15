_scores = {
    "AEIOULNRST": 1, "DG": 2, "BCMP": 3, "FHVWY": 4, "K": 5, "JX": 8, "QZ": 10}
scores = {char: value for chars, value in _scores.items() for char in chars}


class Word(str):
    def score(self):
        return sum(scores.get(c.upper(), 0) for c in self)

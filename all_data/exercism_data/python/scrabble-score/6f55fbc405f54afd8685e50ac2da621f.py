from collections import defaultdict

def _inverse_score_dict(scores):
    inverse_scores = defaultdict(int)
    for score, letters in scores.iteritems():
        inverse_scores.update({l: score for l in letters})
    return inverse_scores

def _sanitize(word):
    return word.upper()

scores_by_letter = {1:  ["A", "E", "I", "O", "U", "L", "N", "R", "S", "T"],
                    2:  ["D", "G"],
                    3:  ["B", "C", "M", "P"],
                    4:  ["F", "H", "V", "W", "Y"],
                    5:  ["K"],
                    8:  ["J", "X"],
                    10: ["Q", "Z"]}

letter_values = _inverse_score_dict(scores_by_letter)

def score(word):
    return sum(letter_values[l] for l in _sanitize(word))

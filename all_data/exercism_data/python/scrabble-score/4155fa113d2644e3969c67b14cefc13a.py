scores_def = {"AEIOULNRST" : 1, 
              "DG"         : 2,
              "BCMP"       : 3,
              "FHVWY"      : 4,
              "K"          : 5,
              "JX"         : 8,
              "QZ"         : 10}

scores = { c : s for chars, s in scores_def.items() for c in chars }

def score(txt):
    return sum(scores.get(x, 0) for x in txt.upper())

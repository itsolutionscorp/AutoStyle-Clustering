from collections import defaultdict

LETTER_GROUPS = ['', 'AEIOULNRST', 'DG', 'BCMP', 'FHVWY', 'K', '', '', 'JX', '', 'QZ']
LETTER_SCORE = defaultdict(int)

for i, group in enumerate(LETTER_GROUPS):
    for l in group:
        LETTER_SCORE[l] = i

def score(word):
    return sum(LETTER_SCORE[l.upper()] for l in word)

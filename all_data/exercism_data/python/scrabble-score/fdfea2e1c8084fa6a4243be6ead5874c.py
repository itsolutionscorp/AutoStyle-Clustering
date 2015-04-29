import re

oldscores = {
    1: 'AEIOULNRST',
    2: 'DG',
    3: 'BCMP',
    4: 'FHVWY',
    5: 'K',
    8: 'JX',
    10: 'QZ'
}

scores = {l: s for s,letters in oldscores.iteritems() for l in letters}

def score(word):
    # Remove all non-alphabetic chars and change to uppercase
    word = re.sub('[^A-Z]+', '', word.upper())

    return sum([scores[letter] for letter in word])

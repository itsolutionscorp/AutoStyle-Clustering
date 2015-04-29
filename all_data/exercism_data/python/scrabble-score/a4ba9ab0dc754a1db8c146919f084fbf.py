import string
letterScores = [1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10]

def score(s):
    return sum([letterScores[ord(c) - ord('a')] for c in filter(lambda x: x in string.ascii_letters, s.lower())])

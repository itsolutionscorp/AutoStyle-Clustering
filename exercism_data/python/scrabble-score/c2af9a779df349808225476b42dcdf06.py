__author__ = 'tracyrohlin'

import re

def score(word):
    scrabble_values = {1: ["a", "e", "i", "o", "u", "l", "n", "r", "s", "t"],
                       2: ["d", "g"],
                       3: ["b", "c", "m", "p"],
                       4: ["f", "h", "v", "w", "y"],
                       5: ["k"],
                       8: ["j", "x"],
                       10: ["q", "z"]}
    scrabble_score = 0
    #if not re.search("\s", word):
    if word:
        lower_word = word.lower()
        for letter in lower_word:
            for key in scrabble_values:
                if letter in scrabble_values[key]:
                    scrabble_score += key
    return scrabble_score

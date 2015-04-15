
LETTER_SCORE = {'z': 10, 'k': 5, 'q': 10, 's': 1, 'w': 4,
                'n': 1, 'h': 4, 'm': 3, 'i': 1, 'e': 1,
                'a': 1, 'd': 2, 'o': 1, 'j': 8, 'p': 3,
                'x': 8, 't': 1, 'b': 3, 'u': 1, 'c': 3,
                'f': 4, 'l': 1, 'r': 1, 'y': 4, 'g': 2, 'v': 4}


def score(letters):
    return sum(LETTER_SCORE[letter]
               for letter in letters.strip().lower())

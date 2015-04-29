letter_value = { 'A': 1, 'E': 1, 'I': 1, 'O': 1, 'U': 1,
                 'L': 1, 'N': 1, 'R': 1, 'S': 1, 'T': 1,
                 'D': 2, 'G': 2, 'B': 3, 'C': 3, 'M': 3,
                 'P': 3, 'F': 4, 'H': 4, 'V': 4, 'W': 4,
                 'Y': 4, 'K': 5, 'J': 8, 'X': 8, 'Q': 10,
                 'Z': 10
                 }  

def score(word):
    word = word.strip()
    word = word.upper()
    score = 0
    for char in word:
        score += letter_value[char]
    return score
    






"""

```plain
Letter                           Value
A, E, I, O, U, L, N, R, S, T       1
D, G                               2
B, C, M, P                         3
F, H, V, W, Y                      4
K                                  5
J, X                               8
Q, Z                               10
```


import unittest

from scrabble import score


class WordTest(unittest.TestCase):
    def test_empty_word_scores_zero(self):
        self.assertEqual(0, score(""))

    def test_whitespace_scores_zero(self):
        self.assertEqual(0, score(" \t\n"))

    def test_scores_very_short_word(self):
        self.assertEqual(1, score('a'))

    def test_scores_other_very_short_word(self):
        self.assertEqual(4, score('f'))

    def test_simple_word_scores_the_number_of_letters(self):
        self.assertEqual(6, score("street"))

    def test_complicated_word_scores_more(self):
        self.assertEqual(22, score("quirky"))

    def test_scores_are_case_insensitive(self):
        self.assertEqual(20, score("MULTIBILLIONAIRE"))

if __name__ == '__main__':
    unittest.main()

"""

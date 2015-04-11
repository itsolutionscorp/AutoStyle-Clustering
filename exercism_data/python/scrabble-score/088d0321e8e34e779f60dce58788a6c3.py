from itertools import izip


LETTER_SCORES = {
    'a': 1, 'b': 3, 'c': 3, 'd': 2, 'e': 1,  'f': 4,
    'g': 2, 'h': 4, 'i': 1, 'j': 8, 'k': 5,  'l': 1,
    'm': 3, 'n': 1, 'o': 1, 'p': 3, 'q': 10, 'r': 1,
    's': 1, 't': 1, 'u': 1, 'v': 4, 'w': 4,  'x': 8,
    'y': 4, 'z': 10
}

class _ScoreModifier(object):
    def __init__(self, letter_modifier, word_modifier):
        self.letter_modifier = letter_modifier
        self.word_modifier = word_modifier
    def letter_score(self, score):
        return score * self.letter_modifier
    def word_score(self, score):
        return score * self.word_modifier

NO = None
DL = _ScoreModifier(2, 1)
TL = _ScoreModifier(3, 1)
DW = _ScoreModifier(1, 2)
TW = _ScoreModifier(1, 3)

def score(word, modifiers = None):
    """
    Compute the scrabble score for the provided word.

    It is possible to provide a list of score modifiers. When you do, the
    modifier list length must be equal to the number of letters in the word.
    The score modifiers that can be used are:

      NO : to indicate that no modifier is to be used
      DL : the letter score is doubled
      TL : the letter score is tripled
      DW : the word score is doubled
      TW : the word score is tripled

    Example use:

    score("PYTHON") -> 14
    score(" Python ") -> 14 (trailing + leading whitespace and case are ignored)
    score(" Python ", [DL,NO,NO,TW,NO,NO]) -> 51 (P doubled and word tripled)
    """

    letter_scores = _get_letter_scores_for_word(word)
    letter_scores = _apply_letter_score_modifiers(letter_scores, modifiers)
    word_score = sum(letter_scores)
    word_score = _apply_word_score_modifiers(word_score, modifiers)
    return word_score

def _get_letter_scores_for_word(word):
    return [
        _get_score_for_letter(letter)
        for letter in word.strip()
    ]

def _get_score_for_letter(letter):
    letter = letter.lower()
    if letter not in LETTER_SCORES:
        raise ValueError("Invalid character found in word: " + letter)
    return LETTER_SCORES[letter]

def _apply_letter_score_modifiers(letter_scores, modifiers):
    if modifiers is None:
        return letter_scores
    if (len(letter_scores) != len(modifiers)):
        raise ValueError("The word length and the list of modifiers are " +
                         "not of equal length")
    return [
        modify.letter_score(letter_score) if modify else letter_score
        for letter_score, modify in izip(letter_scores, modifiers)
    ]

def _apply_word_score_modifiers(word_score, modifiers):
    if modifiers is not None:
        for modify in modifiers:
            if modify:
                word_score = modify.word_score(word_score)
    return word_score

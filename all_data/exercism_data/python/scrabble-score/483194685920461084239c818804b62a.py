# cgi path
# William Morris
# exercism.io
# scrabble.py


LETTER_VALUES = {
    "a": 1, "b": 3, "c": 3, "d": 2, "e": 1,
    "f": 4, "g": 2, "h": 4, "i": 1, "j": 8,
    "k": 5, "l": 1, "m": 3, "n": 1, "o": 1,
    "p": 3, "q": 10, "r": 1, "s": 1, "t": 1,
    "u": 1, "v": 4, "w": 4, "x": 8, "y": 4,
    "z": 10
    }

def score(word,**kwargs):
    total_score = 0
    for letter in word.lower():
        total_score += LETTER_VALUES.get(letter,0)
    for key,value in kwargs.iteritems():
        if key == 'double_letter':
            total_score += LETTER_VALUES.get(value.lower(),0)
        elif key == 'triple_letter':
            total_score += 2*LETTER_VALUES.get(value.lower(),0)
        elif key == 'double_word':
            total_score *= 2**value
        elif key == 'triple_word':
            total_score *= 3**value
        else:
            raise ValueError('Score multiplier argument not found')
            
            
    return total_score

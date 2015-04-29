__author__ = 'jeffmarkey'

from string import ascii_lowercase


def clean(scrabble_word):
    return scrabble_word.lower().replace(' ','').replace('\t','').replace('\n','')


def score(scrabble_word):
    letter_map = {}
    letter_map['a'] = letter_map['e'] = letter_map['i'] = letter_map['o'] = letter_map['u'] = letter_map['l'] = letter_map['n'] = letter_map['r'] = letter_map['s'] = letter_map['t'] = 1
    letter_map['d'] = letter_map['g'] = 2
    letter_map['b'] = letter_map['c'] = letter_map['m'] = letter_map['p'] = 3
    letter_map['f'] = letter_map['h'] = letter_map['v'] = letter_map['w'] = letter_map['y'] = 4
    letter_map['k'] = 5
    letter_map['j'] = letter_map['x'] = 8
    letter_map['q'] = letter_map['z'] = 10
    total = 0
    scrabble_word = clean(scrabble_word)
    for character in scrabble_word:
        total = total + letter_map[character]
    return total

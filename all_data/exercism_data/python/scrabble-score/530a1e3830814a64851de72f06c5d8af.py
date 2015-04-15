import re

conversion = '''A, E, I, O, U, L, N, R, S, T       1
                D, G                               2
                B, C, M, P                         3
                F, H, V, W, Y                      4
                K                                  5
                J, X                               8
                Q, Z                               10'''

matrix = [filter(None, re.split(r'[^\w]+', line)) for line in conversion.split('\n')]

conv_map = {letter.lower(): int(line[-1]) for line in matrix for letter in line[:-1]}

def score(play):
    play = re.sub(r'[^\w]+', '', play.lower())
    return sum(conv_map[letter] for letter in play)

__author__ = 'jimblackler'


def tile_score(tile):
    if 'aeioulnrst'.find(tile) != -1:
        return 1
    if 'dg'.find(tile) != -1:
        return 2
    if 'bcmp'.find(tile) != -1:
        return 3
    if 'fhvwy'.find(tile) != -1:
        return 4
    if 'k'.find(tile) != -1:
        return 5
    if 'jx'.find(tile) != -1:
        return 9
    if 'qz'.find(tile) != -1:
        return 10
    return 0


def score(word):
    return sum(tile_score(letter) for letter in word.lower())

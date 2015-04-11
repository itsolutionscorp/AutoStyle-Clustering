from etl import transform

SCORE_CHAR = {
        1: ['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'],
        2: ['D', 'G'],
        3: ['B', 'C', 'M', 'P'],
        4: ['F', 'H', 'V', 'W', 'Y'],
        5: ['K'],
        8: ['J', 'X'],
        10: ['Q', 'Z']
        }
CHAR_SCORE = transform(SCORE_CHAR)

def score(input):
    def to_score(char):
        return CHAR_SCORE[char.lower()] if char.lower() in CHAR_SCORE else 0
    return sum(map(to_score, input))

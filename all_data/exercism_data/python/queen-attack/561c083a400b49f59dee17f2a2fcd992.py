def test_inputs(func):
    def func_wrapper(pos1, pos2):
        if pos1[0] > 7 or pos1[0] < 0 or pos1[1] > 7 or pos1[1] < 0 or \
           pos2[0] > 7 or pos2[0] < 0 or pos2[1] > 7 or pos2[1] < 0 or \
           pos1 == pos2:
            raise ValueError
        return func(pos1, pos2)
    return func_wrapper

@test_inputs
def board(white, black):
    gameboard = [['_']*8 for x in range(8)]
    gameboard[white[0]][white[1]] = 'W'
    gameboard[black[0]][black[1]] = 'B'

    return [''.join([x for x in gameboard[y]]) for y in range(len(gameboard))]

@test_inputs
def can_attack(white, black):
        diagonal1 = white[0] - white[1]
        diagonal2 = black[0] - black[1]
        diagonal3 = white[0] + white[1]
        diagonal4 = black[0] + black[1]
        if white[0] == black[0] or white[1] == black[1] or \
            diagonal1 == diagonal2 or diagonal3 == diagonal4:
            return True
        return False

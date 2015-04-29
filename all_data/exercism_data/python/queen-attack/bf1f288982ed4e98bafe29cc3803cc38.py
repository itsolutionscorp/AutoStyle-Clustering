def test_inputs(func):
    def func_wrapper(pos1, pos2):
        if any([x > 7 for x in pos1+pos2]) or pos1 == pos2:
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
        if white[0] == black[0] or white[1] == black[1] or \
            white[0] - white[1] == black[0] - black[1] or \
            white[0] + white[1] == black[0] + black[1]:
            return True
        return False

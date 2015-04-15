VALID_CHARS = '+-|* '

def board(game):
    test_board(game)

    new_board = []
    for x in range(len(game)):
        new_board.append([c for c in game[x]])
        for y in range(len(game[x])):
            if game[x][y] == ' ':
                top = game[x-1][y-1:y+2]
                current = game[x][y-1:y+2]
                bottom = game[x+1][y-1:y+2]
                mines = top.count('*') + current.count('*') + bottom.count('*')
                if mines > 0:
                    new_board[x][y] = str(mines)
    return [''.join([str(x) for x in row]) for row in new_board]


def test_board(game):
    if any(len(row) != len(game[0]) for row in game):
        raise ValueError("Uneven Board Size")

    for x in range(len(game)):
        for y in range(len(game[x])):
            if x == 0 or x == len(game):
                if game[x][y] not in '+-':
                    raise ValueError("Bad Board border")
            if y == 0 or y == len(game[x]):
                if game[x][y] not in '+|':
                    raise ValueError("Bad Board border")
            if game[x][y] not in VALID_CHARS:
                raise ValueError("Invalid Chars")

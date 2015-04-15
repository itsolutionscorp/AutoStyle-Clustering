def increment(inp, i, j):
    x = inp[i][j]
    if x == ' ':
        inp[i][j] = '1'
    elif x.isdigit():
        inp[i][j] = str(int(x) + 1)

def check_borders(board):
    w, h = len(board[0]), len(board)

    if (board[0][0], board[-1][0], board[0][-1], board[-1][-1]) != ("+", "+", "+", "+"):
        raise ValueError("faulty border")

    for i in [0, w - 1]:
        for j in range(1, h - 1):
            if board[j][i] != '|':
                raise ValueError("faulty border")

    for i in [0, h - 1]:
        for j in range(1, w - 1):
            if board[i][j] != '-':
                raise ValueError("faulty border")
def board(inp):
    if len(set(len(row) for row in inp)) > 1:
        raise ValueError("invalid board")

    for c in set(''.join(set(''.join(row) for row in inp))):
        if c not in " *+|-":
            raise ValueError("invalid character '%s'" % c)

    check_borders(inp)

    inp = [[x for x in row] for row in inp]

    for i, row in enumerate(inp):
        for j, v in enumerate(row):
            if v == "*":
                for x in range(-1, 2):
                    for y in range(-1, 2):
                        if not (x == 0 and y == 0):
                            increment(inp, i + x, j + y)
                inp[i][j] = "@"

    output = [''.join(row).replace("@", "*") for row in inp]
    return output

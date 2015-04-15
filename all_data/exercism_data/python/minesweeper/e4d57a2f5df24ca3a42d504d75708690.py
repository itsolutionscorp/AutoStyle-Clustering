def board(inp):
    indexes = [
        (-1, -1),
        (-1, 0),
        (-1, 1),
        (0, -1),
        (0, 1),
        (1, -1),
        (1, 0),
        (1, 1)
    ]
    if not _check_board(inp):
        raise ValueError("Faulty input board.")
    inp = [list(row) for row in inp]
    h, w = len(inp), len(inp[0])
    for i in range(1, h - 1):
        for j in range(1, w - 1):
            if inp[i][j] == '*':
                continue
            num = sum(inp[i - ii][j - jj] == '*' for ii, jj in indexes)
            if num:
                inp[i][j] = str(num)
    return [''.join(row) for row in inp]


def _check_board(inp):
    bc = {' ', '*'}
    c1 = all(len(row) == len(inp[0]) for row in inp[1:])
    c2 = all(inp[i][j] == '+' for i, j in [(0, 0), (0, -1),
                                           (-1, 0), (-1, -1)])
    c3 = set(inp[0][1:-1]) == set(inp[-1][1:-1]) == {'-'}
    c4 = all(row[0] == row[-1] == '|' for row in inp[1:-1])
    c5 = all(set(row[1:-1]).union(bc) == bc for row in inp[1:-1])
    return all((c1, c2, c3, c4, c5))

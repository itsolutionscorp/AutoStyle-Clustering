from itertools import product


def board(inp):
    check_board(inp)
    counts = dict()
    out = [bytearray(s, encoding='ascii') for s in inp]
    for i, j in product(range(1, len(inp) - 1), range(1, len(inp[0]) - 1)):
        if inp[i][j] == '*':
            for x, y in get_neighbours(i, j):
                if inp[x][y] == ' ':
                    counts[(x, y)] = counts.get((x, y), ord('0')) + 1
        elif inp[i][j] != ' ':
            raise ValueError('Bad board in position ({0}, {1}): "{2}"!'.format(i, j, inp[i][j]))
    for x, y in counts.keys():
        out[x][y] = counts[(x, y)]
    return [ba.decode(encoding='ascii') for ba in out]

def check_board(inp):
    if any(len(s) != len(inp[0]) for s in inp[1:]) or \
       any(c != '-' for c in inp[0][1:-2] + inp[-1][1:-2]) or \
       any(inp[i][j] != '|' for i, j in product(range(1, len(inp) - 1), [0, len(inp[0]) - 1])) or \
       any(inp[i][j] != '+' for i, j in product([0, len(inp) - 1], [0, len(inp[0]) - 1])): 
        raise ValueError('Bad board!')


def get_neighbours(x, y):
    return [(i, j) for i, j in product(range(x - 1, x + 2), range(y - 1, y + 2)) if i != x or j != y]


if __name__=='__main__':
    print(get_neighbours(5, 6))
    print(get_neighbours(3, 3))

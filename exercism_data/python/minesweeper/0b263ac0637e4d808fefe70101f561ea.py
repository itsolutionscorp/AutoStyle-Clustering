import re

# return a list of all the blank spaces on the board
def get_spaces(inp):
    return [(y, x) for y in range(len(inp)) for x in range(len(inp[y])) if inp[y][x] == ' ']


# return the number of mines surrounding a given coordinate
def count_mines(y, x, inp):
    count = 0
    for i in range(y-1, y+2):
        for j in range(x-1, x+2):
            if inp[i][j:j+1] == '*':
                count += 1
    if count == 0:
        count = ' '
    return count


# validate the input board
def validate_board(inp):
    if inp[0] != inp[-1]:
        raise ValueError("first and last rows must match")
        
    row_len = len(inp[0])
    for row in inp:
        if len(row) != row_len:
            raise ValueError("all rows must be equal length")
        elif re.compile('[^\+\-\|\* ]').search(row):
            raise ValueError('valid input characters are: "+", "-", "|", "*", " "')


# generate the output board
def board(inp):
    validate_board(inp)
    out = inp
    for y, x in get_spaces(inp):
        out[y] = inp[y][:x] + inp[y][x:x+1].replace(' ', str(count_mines(y, x, inp)), 1) + inp[y][x+1:]
    return out

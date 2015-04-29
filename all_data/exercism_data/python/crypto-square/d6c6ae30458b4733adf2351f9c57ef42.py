import math
import re

def encode(text):
    # make lowercase, remove non alphanumerics, convert to list
    text = list(re.sub(r'[^a-z0-9]','',text.lower()))
    # set grid_size to square root of text length, rounded up
    grid_size = int(math.ceil(math.sqrt(len(text))))
    # initialize n by n array
    grid = [['' for x in range(grid_size)] for x in range(grid_size)]
    # populate the n by n array from text
    x = 0
    for i in range(grid_size):
        for j in range(grid_size):
            if x < len(text):
                grid[i][j] = text[x]
                x += 1
    # invert the array and generate a string
    code_str = ''                
    for i in range(grid_size):
        for j in range(grid_size):
            code_str += grid[j][i]
        code_str += ' '
    return code_str.rstrip()

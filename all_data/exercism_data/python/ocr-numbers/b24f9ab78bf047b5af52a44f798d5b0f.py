numbers = [  " _     _  _     _  _  _  _  _ ",
             "| |  | _| _||_||_ |_   ||_||_|",
             "|_|  ||_  _|  | _||_|  ||_| _|",
             "                              ",
]

grids = {}

def grid(n):
    i = int(n) * 3
    return [line[i:i+3] for line in numbers]

def check_grid(g):
    if len(g) != 4:
        raise ValueError("wrong number of rows")

    if set(len(row) for row in g) != set([3]):
        raise ValueError("row too short")

def number(g):
    check_grid(g)

    s = ' '.join(g)
    try: return grids[s]
    except: return "?"

def populate_grids():
    for i in range(10):
        s = ' '.join(grid(i))
        grids[s] = str(i)

populate_grids()

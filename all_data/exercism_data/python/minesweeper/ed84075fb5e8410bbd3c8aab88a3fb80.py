def board(inp):
  if inp == []: return []
  if not border_consistent(inp): raise ValueError

  dimx, dimy = len(inp[0]), len(inp)
  bd = [ list(inp[x]) for x in range(dimy) ]

  for x in range(1,dimx-1):
    for y in range(1,dimy-1):
      if bd[y][x] == ' ': bd[y][x] = get_surrounding_mines(inp,x,y)
      elif bd[y][x] != '*': raise ValueError

  return [ "".join(bd[x]) for x in range(dimy) ]

def get_surrounding_mines(inp, x, y):
  coord = [ (xi, yi) for xi in range(-1,2) 
                     for yi in range(-1, 2) 
                     if xi != 0 or yi != 0 ]
  mines = len([ True for (xi, yi) in coord 
                 if inp[y-yi][x-xi] == '*' ])
  return ' ' if mines == 0 else str(mines)

def border_consistent(inp):
  dimx, dimy = len(inp[0]), len(inp)
  if dimx < 2 or dimy < 2: return False
  top_border = '+' + '-'*(dimx-2) + '+'
  if inp[0]  != top_border: return False
  if inp[-1] != top_border: return False
  if not all(map(lambda x: x[0]  == '|', inp[1:-1])): return False
  if not all(map(lambda x: x[-1] == '|', inp[1:-1])): return False
  if not all(map(lambda x: len(x) == dimx, inp)): return False
  return True

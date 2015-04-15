def board(in_board):

  # Check rows length
  if len(set([ len(row) for row in in_board ])) > 1:
    raise ValueError

  # Check first and last row
  for row in [ in_board[0], in_board[-1] ]:
    if row[0] != '+' or row[-1] != '+' or any([ row[i] != '-' for i in range(1,len(row)-1) ]):
      raise ValueError

  # Check border and char
  for row in in_board[1:-1]:
    if row[0] != '|' or row[-1] != '|' or not all([ row[i] == ' ' or row[i] == '*' for i in range(1,len(row)-1) ]):
      raise ValueError
    
  # Solve
  for r in range(1,len(in_board)-1):
    for c in range(1,len(in_board[r])-1):
      if in_board[r][c] == '*':
        for p in [ (x,y) for x in [c-1,c,c+1] for y in [r-1,r,r+1] ]:
          temp_list = list(in_board[p[1]])
          point = temp_list[p[0]]
          if point not in "+-|*":
            temp_list[p[0]] = '1' if point == ' ' else str(int(point) + 1)
            in_board[p[1]] = "".join(temp_list)

  return in_board

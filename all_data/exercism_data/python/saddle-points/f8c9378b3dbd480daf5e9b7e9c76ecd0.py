def saddle_points(matrix):

  if any([ len(matrix[0]) != len(matrix[i]) for i in range(1,len(matrix)) ]):
    raise ValueError 

  ans = set()

  rows = matrix
  columns = [ list(col) for col in zip(*rows) ]

  for i in range(len(rows)):
    indices = [ index for index, item in enumerate(rows[i]) if item == max(rows[i]) ]
    for j in indices:
      if columns[j][i] == min(columns[j]):
        ans.add((i,j))
    
  return ans

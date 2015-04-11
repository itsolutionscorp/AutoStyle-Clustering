def sum_of_multiples(limit,*multiples):
  sum = 0
  if len(multiples) == 0:
    multiples = [[3,5]] # multiples is a list of lists
  for i in range(0,limit):
    for j in multiples[0]: #should only have one list in multiples
      if j != 0:
        if i%j == 0:
          sum += i
          break
      else:
        multiples[0].remove(j)
        break
    
  return sum

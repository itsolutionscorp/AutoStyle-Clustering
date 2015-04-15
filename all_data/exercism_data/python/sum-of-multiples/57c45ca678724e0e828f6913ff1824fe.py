def sum_of_multiples(n, multiples = [3, 5]):

  multiples_l = []

  for i in multiples:
    if i == 0:
      continue
    j = i
    while (j < n):
      if j not in multiples_l:
        multiples_l.append(j)
      j += i

  return sum(multiples_l)

def sum_of_multiples(number, factors = [3, 5]):
  multiples = []

  for i in range(number):
    for factor in factors:
      if factor != 0 and i % factor == 0 and i not in multiples:
        multiples.append(i)
  return sum(multiples)

def sum_of_multiples(n, factors=[3,5]):
  return sum(get_factors(n, [fac for fac in factors if fac>0]))

def get_factors(n, factors):
  for i in range(1,n):
    for factor in factors:
      if i % factor == 0:
        yield i
        break # don't want to count things twice!

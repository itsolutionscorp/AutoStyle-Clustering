def sieve(n):
  """ build a sieve """
  group = [i for i in range(2,n)]
  i = 2
  while i < n:
    # remove all multiples of i
    j = i
    while j < n:
      j += i
      try:
        group.remove(j)
      except ValueError:
        pass
    # increment i
    i += 1
  return group

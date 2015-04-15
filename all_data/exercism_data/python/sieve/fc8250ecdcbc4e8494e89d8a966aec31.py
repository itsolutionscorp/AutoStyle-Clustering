# The python cookbook has a much better solution (see Google)

def sieve(n):
    L = [True] * n
    res = []

    for i in range(2, n) :
      if L[i]:
         res.append(i)
         for j in range(i*i, n, i):
            L[j] = False
      i = i + 1;
    return res

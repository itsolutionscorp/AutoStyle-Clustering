import array
from math import sqrt

def sieve(n):
  prime = array.array("I",[1 for i in range(n+1)])
  prime[0] = 0
  prime[1] = 0
  prime[2] = 1
  for i in xrange(3,int(sqrt(n))+1,2):
    if prime[i]:
      for j in xrange(i**2,n+1,2*i):
        prime[j] = 0
  res = [2]
  for i in xrange(3,n+1,2):
    if prime[i]:
      res.append(i)
  return res

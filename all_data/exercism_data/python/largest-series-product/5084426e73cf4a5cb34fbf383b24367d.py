from functools import reduce
import operator

def largest_product(s, num):
  sl = slices(s, num)
  return max(reduce(operator.mul, sli, 1) for sli in sl)

def slices(s, num):
  if num > len(s): raise ValueError('n cannot be greater than len(s)')
  s = [int(ss) for ss in s]
  return [s[i:i+num] for i in range(len(s)-num+1)]

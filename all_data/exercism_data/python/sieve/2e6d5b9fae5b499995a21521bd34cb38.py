import math

def sieve(n):

  all_num = range(1, n+1)
  all_num.remove(1)
  
  limit = int(math.sqrt(n)) 

  for i in range(2, limit + 1):
    if i not in all_num:
      continue
    j = all_num.index(i)+1
    while (j < len(all_num)):
      if all_num[j] % i == 0:
        all_num.remove(all_num[j])
      else:
        j += 1

  return all_num

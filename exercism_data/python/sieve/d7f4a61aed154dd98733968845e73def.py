def sieve(n):
  nums = list(range(2,n+1))
  primes = []
  while nums:
    c = nums.pop(0) # remove the 1st element
    primes.append(c)
    for num in nums[:]: # take a copy because we want to modify nums
      if num % c == 0:
        nums.remove(num)
  return primes

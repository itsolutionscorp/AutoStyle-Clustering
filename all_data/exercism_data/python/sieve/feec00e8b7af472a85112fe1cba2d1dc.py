def sieve(limit):
  numbers = range(2,limit+1)
  primes = []
  while len(numbers) > 0:
    divisor = numbers[0]
    primes.append(divisor)
    numbers.remove(divisor)
    for i in numbers:
      if i%divisor == 0:
        numbers.remove(i)
  return primes

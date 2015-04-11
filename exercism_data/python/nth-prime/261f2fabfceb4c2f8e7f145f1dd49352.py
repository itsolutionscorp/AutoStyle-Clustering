import math

def nth_prime(num):
  prime_list = [2]
  prime_index = 1
  current_prime = 3
  while prime_index < num:
    lim = math.sqrt(current_prime)
    if not any([ current_prime % old_prime == 0 for old_prime in prime_list[1:] if old_prime <= lim ]):
      prime_list.append(current_prime)
      prime_index += 1
    current_prime += 2
  return prime_list[-1]

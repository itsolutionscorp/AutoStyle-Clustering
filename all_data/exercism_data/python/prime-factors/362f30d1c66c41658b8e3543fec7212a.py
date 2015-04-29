def prime_factors(num):

  factor_list = []
  factor = 2
  while (num > 1):
    while (num % factor == 0):
      factor_list.append(factor)
      num = num / factor
    factor += 1
    if num > 1 and factor * factor > num:
      factor_list.append(num)
      break

  return factor_list

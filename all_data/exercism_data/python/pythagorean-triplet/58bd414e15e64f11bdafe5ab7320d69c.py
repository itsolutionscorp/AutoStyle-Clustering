def primitive_triplets(b):

  ans = set()
  if b % 4 != 0:
    raise ValueError
  m_n = b / 2
  m_n_factors = factors(m_n)
  for n in m_n_factors:
    m = m_n / n
    if m <= n:
      break
    if (m - n) % 2 != 0 and coprime(m,n):
      item = [m**2-n**2, b, m**2 + n**2]
      ans.add(tuple(sorted(item)))

  return set(sorted(ans))


def triplets_in_range(min_v, max_v):

  ans = set()
  for b in range(1, max_v+1):
    if b % 4 != 0:
      continue
    m_n = b / 2
    m_n_factors = factors(m_n)
    for n in m_n_factors:
      m = m_n / n
      if m <= n:
        break
      if (m - n) % 2 != 0 and coprime(m,n):
        k = 1
        while(k * (m**2 + n**2) <= max_v):
          if (k * (m**2-n**2) >= min_v and k * b >= min_v):
            item = [k * (m**2-n**2), k * b, k * (m**2 + n**2)]
            ans.add(tuple(sorted(item)))
          k += 1

  return set(sorted(ans))


def is_triplet(triplet):

  triplet = sorted(triplet)
  return triplet[0]**2 + triplet[1]**2 == triplet[2]**2


def factors(num):

  factor_list = [1]
  factor = 2
  while (factor < (num/2 + 1)):
    if (num % factor == 0):
      factor_list.append(factor)
    factor += 1
  factor_list.append(num)
  
  return factor_list


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


def coprime(m, n):

  return not any([ item in prime_factors(m) for item in prime_factors(n) ])

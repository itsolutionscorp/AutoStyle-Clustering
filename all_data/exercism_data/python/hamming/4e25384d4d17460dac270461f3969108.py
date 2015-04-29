def distance(dist, ance):
  num = len(dist) if len(dist) >= len(ance) else len(ance)
  count = 0
  for num in range(0, num):
    if list(dist)[num] != list(ance)[num]:
      count += 1

  return count

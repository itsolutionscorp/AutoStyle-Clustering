def distance(source, comparison):
  count = 0

  for index, char in enumerate(source):
    if char != comparison[index]:
      count += 1

  return count

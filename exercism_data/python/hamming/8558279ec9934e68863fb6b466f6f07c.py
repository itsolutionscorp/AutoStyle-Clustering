def distance(first, second):

  if (len(first) != len(second)):
    print "The length of the two sequences is different!"
    return -1

  hamm = 0
  i = 0
  while (i < len(first)):
    if (first[i] != second[i]):
      hamm += 1
    i += 1

  return hamm

def distance(first, second):

  if (len(first) != len(second)):
    print "The length of the two sequences is different!"
    return -1

  return sum(t[0]!=t[1] for t in zip(first,second))

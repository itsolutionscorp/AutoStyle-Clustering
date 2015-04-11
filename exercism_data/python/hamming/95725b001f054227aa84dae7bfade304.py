def distance(left,right):
  return sum(map(lambda (l,r): 1 if l!=r else 0, zip(left,right)))

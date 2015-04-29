def hamming(first, second):
  hamming = 0
  if(len(first) < len(second)):
    a = first
    b = second
  elif(len(first) > len(second)): 
    b = first
    a = second
  else:
    a = first
    b = second
  
  hamming = len(b) - len(a)

  for i in range(len(a)):
    if(a[i] != b[i]):
      hamming += 1

  return hamming

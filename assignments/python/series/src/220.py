def slices(numset,size):
    temp = []
    if size <= len(numset) and size != 0:
      for i in range(len(numset)):
        if i <= len(numset) - size:        
          temp.append(map(int,list(numset[i:i+size])))
    else:
      raise ValueError
    return temp

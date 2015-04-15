def accumulate(array,function):
  out = []
  for el in array:
    out.append(function(el))
  return out

if __name__ == "__main__":
  print accumulate([2],lambda x:x*2)

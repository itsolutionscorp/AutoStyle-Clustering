def distance(original, copy):
  mutations = 0
  
  for i in range(len(original)):
    if len(copy) - 1 < i:
      break
    
    if original[i] != copy[i]:
      mutations += 1
  
  return mutations
  
  

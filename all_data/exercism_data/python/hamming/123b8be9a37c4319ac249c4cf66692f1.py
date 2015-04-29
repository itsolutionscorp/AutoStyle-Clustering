
def distance(strand1,strand2):
  str1 = str.strip(strand1)
  str2 = str.strip(strand2)  
  if len(str1) != len(str2):
    return
  else:
    sum = 0    
    for i in range(len(str1)):
      if str1[i] != str2[i]:
        sum += 1
  
    return sum
      
    

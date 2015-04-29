def slices(string, width):
 result=[]
 if (width>len(string)) or width ==0:
  raise ValueError
 for index in range(0,len(string)-width+1):
  result.append([int(x) for x in string[index:index+width]])
 return result

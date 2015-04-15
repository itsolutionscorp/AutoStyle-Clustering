
def sieve(endpt):  
  numlist = range(2,endpt+1)
    
  primelist = []  
  while len(numlist) > 0:
    factor = numlist[0]    
    primelist.append(factor)
    numlist = numlist[1:]
    numlist = [x for x in numlist if x % factor != 0]
    
  return primelist
  

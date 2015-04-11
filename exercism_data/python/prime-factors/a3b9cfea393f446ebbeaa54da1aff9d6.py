import math

def prime_factors(number):
  primeFactor = []
  numLeft = number
  num = 2
  check = [2, 3, 5, 7]
  
  if number in check:
    primeFactor.append(number)
    return primeFactor

  while num <= number:
    prime = True
    cont = True
    for i in range(2, int(math.sqrt(num)) + 1):
      if num % i == 0:
	prime = False
    if prime == False:
      num += 1
      
    while prime == True and cont == True:
      if numLeft % num == 0:
	primeFactor.append(num)
	numLeft = numLeft / num
	if numLeft == 1:
	  return primeFactor
      else:
	cont == False
	num += 1
	break
      
  if len(primeFactor) == 0 and number != 1:
    primeFactor.append(number)
  return primeFactor
#print prime_factors(6)

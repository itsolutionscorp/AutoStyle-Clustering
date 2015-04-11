import sys

def largest_palindrome(max_factor, min_factor=0):
  largest = 0
  factors = []
  for i in range(min_factor, max_factor+1):
    for j in range(i, max_factor+1):
      product = i * j
      if str(product) == str(product)[::-1] and product > largest:
        largest = product
        factors = [i, j]
  return (largest, factors)

def smallest_palindrome(max_factor, min_factor=0):
  smallest = sys.maxsize 
  factors = []
  for i in range(min_factor, max_factor+1):
    for j in range(i, max_factor+1):
      product = i * j
      if str(product) == str(product)[::-1] and product < smallest:
        smallest = product
        factors = [i, j]
  return (smallest, factors)

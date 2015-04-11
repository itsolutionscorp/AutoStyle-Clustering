def slices(string, width):
 if width>len(string):
  raise ValueError
 result=[]
 for index in range(len(string)-width+1):
  result.append(map(int,string[index:index+width]))
 return result

def product_of_list(thelist):
 product = 1
 for element in thelist:
  product = element*product
 return product

def largest_product(string, width):
 if width>len(string):
  raise ValueError
 result=[]
 for element in slices(string, width):
  result.append(product_of_list(element))
 return max(result)

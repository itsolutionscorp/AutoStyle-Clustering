def slices(number, slice_count):
  list_num = [int(x) for x in str(number)]
  if slice_count <= 0 and number: #empty string is falsy
    raise ValueError("Cannot slice a series of length 0")
  elif slice_count > len(list_num):
    raise ValueError("Cannot slice series longer than the given number")
  elif slice_count == 0 and not number:
    return [[1]]
  else: #good input
    output = []
    for i in range(0,len(list_num)-slice_count+1):
      output.append(list(list_num[i:i+slice_count]))
    return output
      
      
def largest_product(series, slice_count):
  input = slices(series, slice_count)    
  output = []
  for list in input:
    output.append(reduce(lambda x, y: x * y,  list))
  return max(output)

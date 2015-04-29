def slices(number, slice_count):
  list_num = [int(x) for x in str(number)]
  if slice_count <= 0:
    raise ValueError("Cannot slice a series of length 0")
  elif slice_count > len(list_num):
    raise ValueError("Cannot slice series longer than the given number")
  else: #good input
    output = []
    for i in range(0,len(list_num)-slice_count+1):
      output.append(list(list_num[i:i+slice_count]))
    return output

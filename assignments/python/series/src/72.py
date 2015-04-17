def slices(number_string,slices):
  section_slice = []
  output        = []
  number_len    = len(number_string)
  if not (1 <= slices <= number_len):
    raise ValueError('watashiwa')
  for i in range(0,number_len):
    for val in range(i,i+slices):
      if (i+slices) <= (number_len):
        section_slice.append(int(number_string[val]))
    if section_slice!=[]:    
      output.append(section_slice)
    section_slice=[]
  return output

def to_rna(input):

  dnaTypes = {"G":"C","C":"G","T":"A","A":"U"}

  output = []
  for each in input:
    value =  dnaTypes.get(each,'not found')
    output.append(value)
    
  return ''.join(output)





 

  

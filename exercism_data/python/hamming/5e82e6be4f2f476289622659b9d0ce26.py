def distance(input1, input2) : 
  minlength = min(len(input1), len(input2))
  return sum (input1[i] != input2[i] for i in range(minlength))

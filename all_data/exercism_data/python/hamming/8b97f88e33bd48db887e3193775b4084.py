def distance(first_sequence, second_seqence):
   return sum([1 for i in range(len(first_sequence))\
       if first_sequence[i] != second_seqence[i]])

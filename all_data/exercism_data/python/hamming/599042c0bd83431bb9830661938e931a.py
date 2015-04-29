def distance(string1, string2):
  hamming_count = 0

  for str1, str2 in zip(string1, string2):
    if str1 != str2:
      hamming_count +=1
  return hamming_count

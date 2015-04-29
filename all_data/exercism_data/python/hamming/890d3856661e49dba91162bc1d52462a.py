def distance(string1, string2):
  return len([str1 for str1, str2 in zip(string1, string2) if str1 != str2])

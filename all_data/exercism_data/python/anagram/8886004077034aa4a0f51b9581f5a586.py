# words A and B are anagrams of each other iff each can be rearranged into the other. Equivalently, each can be rearranged into the same string. We can use Python's sort function to do this.

def string_to_list(string):
 return [letter for letter in string]

def are_anagrams(first, second):
 this=string_to_list(first.lower())
 that=string_to_list(second.lower())
 this.sort()
 that.sort()
 if this == that and (first.lower() != second.lower()):
  return True
 else:
  return False


def detect_anagrams(word, candidates):
 temp=[]
 for candidate in candidates:
  first=word
  second=candidate
  if are_anagrams(first, second) == True:
   temp.append(candidate)
 return temp

SUBLIST = 0
SUPERLIST = 1
EQUAL = 2
UNEQUAL = 3

def check_lists(first, second):

  if first == second:
    return EQUAL

  if sublist_helper(first, second):
    return SUBLIST
    
  if sublist_helper(second, first):
    return SUPERLIST

  return UNEQUAL

def sublist_helper(first, second):
  if len(first) < len(second):
    if len(first) == 0:
      return True
    temp = second
    for i in range(len(first)):
      if first[i] not in second:
        break
      temp.remove(first[i])
      if i == len(first) - 1:
        return True
  return False

codes="GACTU"


def distance(first, second):
 if (len(first) != len(second)) or (first=="") or (second==""):
  return -1
 for index in range(len(first)):
  if not (first[index] in codes):
   return -1
 for index in range(len(second)):
  if not (second[index] in codes):
   return -1
 distance = 0
 for index in range(len(first)):
  if first[index]!=second[index]:
   distance=distance+1
 return distance

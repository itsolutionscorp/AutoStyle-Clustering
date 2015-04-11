allergies=['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']

class Result:
 def __init__(self):
  list = []
 def is_allergic_to(self,food):
  if food in self.list:
   return True
  else:
   return False


def binary_rep(score):
 temp = "{0:b}".format((score%256))
 return (8-len(temp))*'0'+temp


def bin_to_list(string):
 result = ([x for x in string])
 result.reverse()
 return result

def gather_indices(thelist):
 result=[]
 for index in range(len(thelist)):
  if thelist[index]=='1':
   result.append(index)
 return result

def get_allergies(theindices):
 result=[]
 for index in theindices:
  result.append(allergies[index])
 return result

def theallergies(N):
 return get_allergies(gather_indices(bin_to_list(binary_rep(N))))

def Allergies(N):
 result = Result()
 result.list = theallergies(N)
 return result

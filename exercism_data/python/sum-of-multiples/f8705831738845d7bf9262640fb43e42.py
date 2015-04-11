def produce_list(N):
 return range(1,N)


def themarks(N,number):
 thelist = produce_list(N)
 marks=[0]*len(thelist)
 if number == 0:
  return marks
 for index in range(len(thelist)):
  if thelist[index]%number == 0:
   marks[index]=1
 return marks


def collect(N, list_of_numbers):
 thelist=produce_list(N)
 result=[]
 list_of_marks=[]
 for number in list_of_numbers:
  list_of_marks.append(themarks(N,number))
 for mark in list_of_marks:
  for index in range(len(mark)):
   if mark[index]==1:
    result.append(thelist[index])
 pruned_result=[]
 for element in result:
  if not (element in pruned_result):
   pruned_result.append(element)
 return pruned_result

def sum_of_multiples(N, list_of_multiples=[3,5]):
  return sum(collect(N,list_of_multiples))

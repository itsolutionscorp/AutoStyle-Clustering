def update_indices(indices, index, thelist):
 multiple=thelist[index]
 hops=int(round((len(thelist)-index)/float(multiple),0))
 if hops!=0:
  for hop in range(1,hops):
   indices[index+hop*multiple]=0
 return indices

def sieve(N):
 thelist=range(2,N+1)
 indices=[1]*(len(thelist))
 for index in range(0,len(thelist)):
  if indices[index]==0:
   continue
  else:
   update_indices(indices, index, thelist)
 result=[]
 for index in range(len(indices)):
  if indices[index] == 1:
   result.append(thelist[index])
 return result

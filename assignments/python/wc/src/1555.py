def word_count(string):
  wrdlist=string.split()
  ans=dict()
  l=len(wrdlist)
  i=0
  while(i<l):
    cnt=1
    j=i+1
    while(j<l):
      if wrdlist[j]==wrdlist[i]:
        cnt+=1
        wrdlist.pop(j)
        l-=1
      else:
        j+=1
    ans[wrdlist[i]]=cnt
    i+=1
  return ans

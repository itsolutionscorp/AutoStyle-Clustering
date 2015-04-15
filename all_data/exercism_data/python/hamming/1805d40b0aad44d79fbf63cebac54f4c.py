def distance(strand1,strand2):
  if(len(strand1)!=len(strand2)):
    return (-1)
  else:
    ham_dist=0
    cstrand = zip(strand1,strand2)
    for (n1,n2) in cstrand:
      if (n1!=n2) :
        ham_dist +=1
      else:
        ham_dist +=0
    return (ham_dist)

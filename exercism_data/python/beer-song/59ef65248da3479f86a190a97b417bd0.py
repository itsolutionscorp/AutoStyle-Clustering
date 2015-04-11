class Beer(object):
  def __init__(self):
    pass

  def verse(self,N):
    n_N	  = "{0}".format(N)
    n_N1  = "{0}".format(N-1)
    if (N==0):
      n_N = "no more"
      n_N1= "{0}".format(99)
    if (N==1):
      n_N1 = "no more"
    out = "{0} bottles of beer on the wall, {1} bottles of beer.\nTake one down and pass it around, {2} bottles of beer on the wall.\n".format(n_N.capitalize(),n_N,n_N1)
    if (N==0):
      out = out.replace("Take one down and pass it around","Go to the store and buy some more")
    if (N==1):
      out = out.replace("bottles","bottle",2)
      out = out.replace("one","it")
    if (N==2):
      out = out[:out.rfind("bottles")]+out[out.rfind("bottles"):].replace("ttles","ttle")
    return out

  def sing(self,N1,N2=0):
    out = ''
    for i in range(N1-N2+1):
      out += self.verse(N1-i)
      out += '\n'
    return out

if __name__ == "__main__":
  print Beer().verse(5)
  print Beer().verse(1)
  print Beer().verse(0)
  print Beer().sing(6,3)
  print Beer().sing(3)
      

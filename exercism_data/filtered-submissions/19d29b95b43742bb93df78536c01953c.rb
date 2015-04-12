class Hamming
end

def Hamming.compute(a, b )
  koniec = (a.size > b.size) ? b.size : a.size
  i = 0
zwrot = 0
while(koniec > i)
  if(a[i] != b[i])
    zwrot+=1
  end
  i+=1
end
return zwrot
end

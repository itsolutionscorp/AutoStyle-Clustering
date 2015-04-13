def compute(firstvar,secondvar)



    flen = firstvar.length
    slen = secondvar.length
    firstvar.chars
    secondvar.chars
    badcount = 0
    i = 0
    while i <= flen-1 do
      if firstvar[i] != secondvar[i]
        badcount +=1
      end
      i+=1
    end
    return badcount
  end
def compute(x,y)
    
    diffs = 0    
    i=0
    
    while i < x.length && i <y.length do
      diffs +=1 if x[i] != y[i]
      i += 1
    end
    
    return diffs
    
  end
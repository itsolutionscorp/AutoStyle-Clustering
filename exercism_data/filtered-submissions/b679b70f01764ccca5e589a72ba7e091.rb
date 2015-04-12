def compute(x,y)
    if x == y 
      0
    else
      first = x.split("")
      second = y.split("")
      sequence_length = first.length
      i = 0
      discrepencies = 0
      while i < sequence_length
        puts first[i]
        puts second[i]
        if first[i] == second[i]
          
        else
          discrepencies += 1
        end
        i += 1
      end
      return discrepencies
    end
  end
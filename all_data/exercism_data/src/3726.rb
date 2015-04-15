def compute(strand1, strand2)
    
    diff = 0 
    return diff if strand1 == strand2   
    
    (0..strand1.length).each do | i |
      diff += 1 if strand1[i] != strand2[i] 
    end
    
    return diff
  end
def compute(strand1, strand2)    
    count = 0
    i = 0    
    loop do
      count += 1 if strand1[i] != strand2[i]
      i += 1
      break if i >= strand1.length || i >= strand2.length
    end
    return count
  end
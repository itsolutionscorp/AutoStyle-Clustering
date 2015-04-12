def compute(strand1, strand2)
    difference = 0
    
    for i in 0..strand1.length
      difference += 1 unless strand2[i] == strand1[i] 
    end

    difference
  end
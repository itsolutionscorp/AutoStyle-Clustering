def compute(strand1, strand2)
    count = 0
    for i in 0..strand1.length do 
      count += 1  if strand1[i] != strand2[i]     
    end   
    count
  end
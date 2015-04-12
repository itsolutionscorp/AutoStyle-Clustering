def compute(n1, n2)
    diff_count  = 0
    strand_count   = 0

    while strand_count < n1.length  
      diff_count += 1 if n1[strand_count] != n2[strand_count]
      strand_count += 1
    end
    
    diff_count
  end
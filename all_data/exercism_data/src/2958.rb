def compute(a,b)
    ham_no =0
    i= [a,b].min.size 
    while (i >= 0) 
      if a[i] != b[i]
        hamming +=1 unless (a[i] ==nil || b[i] ==nil) 
      end
      i+= -1 
    end
    return ham_no  
  end
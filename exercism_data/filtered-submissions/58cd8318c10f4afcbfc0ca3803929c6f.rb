def compute(a1, a2)
    res = 0	
    if a1==a2
      res	   
    else
      for pos in 0..a1.length-1
        if a1[pos]!= a2[pos]
          res = res +1 
        end
      end
      res
    end
  end
def compute(arg1, arg2)
    sum = 0

    arg1_len = arg1.length
    arg2_len = arg2.length

    if arg1_len < arg2_len
      len = arg1_len
    elsif arg1_len > arg2_len
      len = arg2_len
    else
      len = arg2_len
    end
        
      
    i = 0

  	while i <= len-1
    	if arg1[i] != arg2[i]
    		sum += 1
      end
      i+=1
    end

    return sum
  end
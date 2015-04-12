def compute(bit1, bit2)
    return nil if bit1.size != bit2.size
    return_me = 0
    return return_me if bit1 == bit2
    
    index = 0
    while index < bit1.size
      return_me += 1 unless bit1[index] == bit2[index]
      index += 1
    end
    return_me
  end
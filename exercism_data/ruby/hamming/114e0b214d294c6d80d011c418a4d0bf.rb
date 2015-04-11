class Hamming 
  def self.compute(left, right) 
    if left.length != right.length
      raise "DNA strands are different length" 
    end   
    compute_hamm_diff(left.upcase.chars, right.upcase.chars)
  end  
 
  private   

  def self.compute_hamm_diff(left, right)   
    diff = 0
    for i in (0 ... left.length)  
      diff += 1 if left[i] != right[i]
    end   
    diff
  end 
end

class Hamming 
  def self.compute(left, right) 
    if left.length != right.length
      raise "DNA strands are different length" 
    end   
    compute_hamm_diff(left.upcase.chars, right.upcase.chars)
  end  
 
  private   

  def self.compute_hamm_diff(left, right)   
    zipped = left.zip right  
    zipped.count { |l, r| l != r }
  end 
end

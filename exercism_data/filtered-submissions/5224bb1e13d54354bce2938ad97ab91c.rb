class Hamming
  
  def compute(base,comp)
    val = 0 
    base.chars.each_with_index do |c,idx|      
      val += 1 unless c == comp[idx]
    end
    val
  end
  
end

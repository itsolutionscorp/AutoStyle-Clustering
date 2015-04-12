class Hamming
  
  def compute(a,b)
    val = 0 
    a = a.split('')
    b = b.split('')
    a.each_with_index do |e,idx|      
      val += 1 unless e == b[idx]
    end
    val
  end
  
end

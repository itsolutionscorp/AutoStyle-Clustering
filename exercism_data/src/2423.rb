class Hamming
  
  def compute(a,b)
    return 0 if a == b
    a.size > b.size ? min = b.size : min = a.size 
    (0...min).count {|i| a[i] != b[i] }
  end

end

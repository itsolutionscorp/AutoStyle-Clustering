class Hamming

  def compute(a, b)
    result = 0
    
    n = a.length > b.length ? b.length : a.length
    
    for i in 0...n 
      result += 1 if a[i] != b[i]
    end

    result
  end

end

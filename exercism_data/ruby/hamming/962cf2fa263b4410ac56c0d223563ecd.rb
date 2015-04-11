class Hamming

  def self.compute(strand_a, strand_b)
    i = 0
    distance = 0
    
    until ( i > strand_a.length)
      unless strand_a[i] == strand_b[i] 
        distance = distance + 1
      end
      i = i + 1
    end
    distance
  end
end

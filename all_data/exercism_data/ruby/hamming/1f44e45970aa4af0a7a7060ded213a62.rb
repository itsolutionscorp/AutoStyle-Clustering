class Hamming

  def self.compute(strand1, strand2)
    difference = 0
    
    for i in 0..strand1.size do
      if strand1[i] and strand2[i]
        if strand1[i] != strand2[i] 
          difference += 1
        end
      end
    end

    difference
  end

end

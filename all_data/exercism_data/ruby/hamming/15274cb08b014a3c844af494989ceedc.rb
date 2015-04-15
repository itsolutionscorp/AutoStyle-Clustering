class Hamming  
  def self.compute(s1,s2)
    ham = 0
    min = [s1.length,s2.length].min
    for i in 0..(min-1)
      if s1[i] != s2[i]
        ham += 1
      end
    end
    return ham
  end
end

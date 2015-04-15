class Hamming

  def self.compute(strand_a, strand_b)
    i = 0
    counter = 0
    strand_a.each_char do |n|
      if strand_b[i] == nil then return counter end
      if n != strand_b[i] then counter += 1 end
      i += 1
    end
    counter
  end  

end

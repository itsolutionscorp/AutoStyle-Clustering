class Hamming

  def self.compute(original, copy)
  	hamming_gap = 0
  	n = 0
  	common_size = [original.size, copy.size].min

  	while n < common_size do
      hamming_gap += 1 if self.mutant?(original[n], copy[n])
      n += 1
    end

   	hamming_gap
  end

  def self.mutant?(original, copy)
   	not original.eql? copy
  end

end

class Hamming
  def self.compute(strand_1, strand_2)
  	if strand_1.length == strand_2.length
      self.calculate_hamming_distance strand_1, strand_2
  	else
 	  "Hamming distance is only defined for sequences of equal length."
  	end
  end

  def self.calculate_hamming_distance(strand_1, strand_2)
    hamming_distance, counter, strand_1, strand_2 = 0, 0, strand_1.split(''), strand_2.split('') 
  	until counter == strand_1.length
  	  hamming_distance += 1 if strand_1[counter] != strand_2[counter]
  	  counter += 1
  	end
  	hamming_distance  
  end
end

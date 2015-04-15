class Hamming

  def self.compute(original, copy)
    distance = 0    

    common_length(original, copy).times.count do |n| 
 		  distance += 1 if original[n] != copy[n]
    end
  end

  def self.common_length(original, copy)
		[original.size, copy.size].min
  end


end

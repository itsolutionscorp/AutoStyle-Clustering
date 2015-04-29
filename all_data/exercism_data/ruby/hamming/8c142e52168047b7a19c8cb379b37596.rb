class Hamming
  def self.compute(first_string, second_string)
	small_len = (first_string.length<second_string.length)?(first_string.length):(second_string.length)
	count = 0
	for i in 0..small_len-1
		if first_string[i] != second_string[i] then
			count=count+1
		end 	
	end 
	return count
   end
  
end
puts Hamming.compute("wert","wett")

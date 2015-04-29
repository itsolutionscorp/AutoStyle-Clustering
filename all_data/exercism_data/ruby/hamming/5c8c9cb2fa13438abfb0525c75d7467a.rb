class Hamming

	def self.compute(strand1, strand2)
		strand1.chars.map.with_index do |letter, index| 
			letter == strand2.chars[index]     
		end.count(false)
	end

end

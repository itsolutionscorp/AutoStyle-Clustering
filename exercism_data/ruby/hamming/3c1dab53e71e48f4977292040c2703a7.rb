class Hamming
	def self.compute(seq_1, seq_2)
		min_length = [seq_1.size, seq_2.size].min

		corresponding_chars = seq_1[0,min_length].chars.zip(seq_2.chars)
		
		corresponding_chars.count do |pair| 
			pair[0] != pair[1]
		end
	end
end

class Hamming
	def self.compute(strand1, strand2)
		count = 0
		strand1.downcase.each_char.with_index do |char, index|
			second_char = strand2.downcase[index]
			unless second_char == nil
				if char != second_char
					count += 1 
				end
			end
		end
		count
	end
end

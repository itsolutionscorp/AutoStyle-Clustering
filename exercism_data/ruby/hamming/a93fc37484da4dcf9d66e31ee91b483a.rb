class Hamming

	def self.compute(strand1,strand2)
		@distance = 0
		strand1Letters = strand1.split('')
		strand2Letters = strand2.split('')
		strand1Letters.each_with_index do |letter, index|
			if (letter == strand2Letters[index] || strand2Letters[index].nil?)
				@distance = @distance
			else
				@distance += 1
			end
		end
		return @distance
	end

end

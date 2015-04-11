class Hamming

	def self.compute(strand1,strand2)
		@distance = 0
		strand1Letters = strand1.split('')
		strand2Letters = strand2.split('')
		strand1Letters.each_with_index do |letter, index|
			if (lettersAreIdentical?(letter,strand2Letters,index) || firstStrandIsLonger?(letter,strand2Letters,index))
				@distance = @distance
			else
				@distance += 1
			end
		end
		return @distance
	end

	def self.lettersAreIdentical?(letter,strand2Letters,index)
		if letter == strand2Letters[index]
			return true
		end
	end

	def self.firstStrandIsLonger?(letter,strand2Letters,index)
		if strand2Letters[index].nil?
			return true
		end
	end

end

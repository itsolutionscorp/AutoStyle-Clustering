class Hamming

	class  << self
		def  compute(strandOne, strandTwo)
			combined = _stringToCharArray(strandOne).zip(_stringToCharArray(strandTwo))
			combined.count { |a, b| a != b }
		end

	private

		def _stringToCharArray(str)
			str.scan(/./)
		end
	end
end

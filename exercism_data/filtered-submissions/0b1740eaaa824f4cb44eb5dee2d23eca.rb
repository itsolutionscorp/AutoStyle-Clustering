class Hamming
	class  << self
		def  compute(strandOne, strandTwo)
			combined = strandOne.chars.zip(strandTwo.chars)
			combined.count(String.method(&:eql))
		end
	end
end

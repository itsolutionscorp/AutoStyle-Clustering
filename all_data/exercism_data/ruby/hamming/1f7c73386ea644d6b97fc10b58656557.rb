class Hamming
	def self.compute(original, other)
		length = length_of_shorter(original, other)
		differences = 0
		for i in 0..length-1 do
			differences+=1 if original[i] != other[i] 
		end
		return differences
	end

	private
	def self.length_of_shorter(a, b)
		return (a.length > b.length)? b.length : a.length
	end
end

class Hamming
	def self.compute(original, other)
		length = length(original, other)
		differences = 0
		(0..length-1).each do |i| 
			differences +=  original[i] == other[i] ? 0 : 1
		end
		differences
	end

	private
	def self.length(a, b)
		(a.length > b.length)? b.length : a.length
	end
end

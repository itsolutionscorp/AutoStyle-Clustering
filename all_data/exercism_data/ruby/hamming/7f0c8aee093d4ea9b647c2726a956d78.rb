class Hamming

	def initialize
		@basepairs = []
	end

	def self.compute(a, b)
		basepairs = (a.chars.each_with_index.to_a - b.chars.each_with_index.to_a)
		return basepairs.length
	end

end

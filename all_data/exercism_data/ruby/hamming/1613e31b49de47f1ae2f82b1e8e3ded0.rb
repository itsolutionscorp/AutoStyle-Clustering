class Hamming
	def self.compute(x,y)
		compare(x,y).count
	end

	def self.compare
		comparison = x.chars.zip(y.chars)
		comparison.keep_if{|a,b| (a != b) && b}
	end
end

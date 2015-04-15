class Hamming
	def self.compute (x, y)
		ary = x.chars.take(y.size).zip(y.chars)	
		ary.count {|n1, n2| n1 != n2 }
	end
end

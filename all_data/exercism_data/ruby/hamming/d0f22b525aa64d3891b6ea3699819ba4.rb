class Hamming
	def self.compute(a,b)
		stringToArray(a)
			.zip(stringToArray(b))
			.select {|pair| pair[0] != pair[1] && pair.compact.count == 2 }
			.count
	end

	def self.stringToArray(string)
		string.scan(/\w/)
	end
end

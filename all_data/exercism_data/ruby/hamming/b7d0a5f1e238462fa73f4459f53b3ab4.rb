class Hamming

	def self.compute(first, second)
		short_string, long_string = [first, second].sort_by{ |s| s.length }
		short_string.chars.reject.with_index{ |char, i| char == long_string[i]}.length
	end

end

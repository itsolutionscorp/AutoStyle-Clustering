class Hamming

	def self.compute(s1, s2)
		s1.chars.each_with_index.inject(0) { |distance, (char, index)| distance += (char == s2[index] ? 0 : 1) }
	end

end

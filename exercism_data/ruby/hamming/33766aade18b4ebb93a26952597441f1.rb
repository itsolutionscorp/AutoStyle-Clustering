class Hamming

	def self.compute s1, s2
		distance = 0
		s1.chars.each_with_index { |char, i| distance += char == s2[i] ? 0 : 1}
		distance
	end
	
end

class Hamming
	def self.compute a, b
		a.chars.select.with_index { |char, i| b[i] != char }.length
	end
end
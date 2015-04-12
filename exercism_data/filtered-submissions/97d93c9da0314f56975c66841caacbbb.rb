class Hamming
	def compute(a, b)
		a.chars.zip(b.chars).count { |pair| pair[0] != pair[1] }
	end
end

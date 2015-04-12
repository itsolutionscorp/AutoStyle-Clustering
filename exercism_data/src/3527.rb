class Hamming

	def compute(s1, s2)
		s1.chars.zip(s2.chars).inject(0) do |distance, (c1, c2)|
			distance + (c1 == c2 ? 0 : 1)
		end
	end

end

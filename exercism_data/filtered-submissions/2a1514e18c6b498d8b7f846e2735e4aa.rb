class Hamming
	def compute(s1, s2)
		if s1.length > s2.length
			s1, s2 = s2, s1
		end
		d = 0
		s1.each_char.with_index do |c1, i|
			d += 1 if c1 != s2[i]
		end
		d
	end
end

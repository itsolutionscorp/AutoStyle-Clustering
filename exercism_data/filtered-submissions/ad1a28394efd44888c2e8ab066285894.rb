class Hamming
	def compute(s1,s2)
		return 0 if s1 == s2

		hd = 0

		s1.length.times do |i|
			hd += 1 if s1[i] != s2[i]
		end

		return hd

	end
end

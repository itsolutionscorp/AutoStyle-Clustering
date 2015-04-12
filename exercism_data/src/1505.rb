class Hamming
	def Hamming.compute(s1, s2)
		if s1 == s2
			return 0
		else
			i = 0
			h = 0
			while s1[i] && s2[i] do
				if s1[i] != s2[i]
					h += 1
				end
				i += 1
			end
			return h
		end
	end
end

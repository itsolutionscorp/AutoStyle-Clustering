class Hamming
	def self.compute s1, s2
		if s1.empty? or s2.empty?
			0
		elsif s1[0] == s2[0]
			compute(s1.chopf,s2.chopf)
		else
			1 + compute(s1.chopf,s2.chopf)
		end
	end
end

class String
	def chopf
		self.reverse.chop.reverse
	end
end

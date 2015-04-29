class Hamming

	def self.compute(a, b)
		a.each_codepoint.zip(b.each_codepoint).select {|l, r| l != r}.length
	end	

end

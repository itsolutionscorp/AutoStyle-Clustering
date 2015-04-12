class Hamming
	def compute(s1,s2)
		(0...[s1.length,s2.length].min).select { |i| s1[i] != s2[i] }.length
	end
end
		
	

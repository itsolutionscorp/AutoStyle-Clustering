class Hamming
	def compute(strand1, strand2)
		(0...[strand1.length, strand2.length].min).count do |x|
			strand1[x] != strand2[x]
		end
	end
end

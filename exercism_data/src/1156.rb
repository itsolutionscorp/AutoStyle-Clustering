class Hamming

	def compute(strand1, strand2)
		sample1 = strand1.chars
		sample2 = strand2.chars
		count = 0
		sample1.each_index do |index|
			(count +=1) if sample1[index] != sample2[index]
		end
		count
	end

end

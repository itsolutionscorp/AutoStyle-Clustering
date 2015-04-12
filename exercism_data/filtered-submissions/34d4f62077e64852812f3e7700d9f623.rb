class Hamming

	def compute(strand1,strand2)
		hamming_distance = 0
		for i in 0...[strand1.length,strand2.length].min
			hamming_distance +=1 if strand1[i] != strand2[i]
		end
		hamming_distance
	end
end

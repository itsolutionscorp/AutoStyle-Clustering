class Hamming
	def compute(strand1, strand2)
		distance = 0
		for i in 0..[strand1.length, strand2.length].min-1 do
			distance +=1 if strand1[i] != strand2[i]
		end
		return distance
	end	
end

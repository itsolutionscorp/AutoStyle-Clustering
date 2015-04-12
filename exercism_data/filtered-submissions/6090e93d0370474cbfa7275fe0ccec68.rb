class Hamming
	def compute (strand1, strand2)
		
		if strand1.length < strand2.length 
			length = strand1.length
		else
			length = strand2.length
		end


		count = 0
		i=0
		while i < length do
			if strand1[i] != strand2[i]
				count+=1
			end
			i+=1
		end
		count
	end
end

class Hamming
	def compute (strand1, strand2)
		
		length = [strand1.length, strand2.length].min

		i=0
		count = 0
		while i < length do
			if strand1[i] != strand2[i]
				count+=1
			end
			i+=1
		end
		count
	end
end

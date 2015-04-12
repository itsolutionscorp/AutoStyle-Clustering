def compute(dna1, dna2)
	
		count=0

		if dna1.length > dna2.length then

			for i in 0...dna2.length

				count+=1 unless dna1[i] == dna2[i]		

			end	

		else
	
			for i in 0...dna1.length

				count+=1 unless dna1[i] == dna2[i]		

			end		
		end

		return count 
    end
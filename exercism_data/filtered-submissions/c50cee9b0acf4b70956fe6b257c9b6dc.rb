def compute(strand1, strand2)
		difference = 0
		
		max = [strand1.length, strand2.length].min

		for i in 0...max
			strand1[i] != strand2[i] ? difference += 1 : nil
		end
		
		difference
	
	end
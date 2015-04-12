def compute(str1, str2)
		loop_length = str1.length > str2.length ? str2.length : str1.length 
		difference = 0
		for i in 0..loop_length-1
			difference += 1 unless str1[i] == str2[i]	
		end
		difference
	end
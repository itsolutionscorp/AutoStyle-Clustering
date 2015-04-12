class Hamming

	def Hamming.compute(string1, string2)
	
		if /^[^ACGT]$/.match(string1) || /^[^ACGT]$/.match(string2) then
			'Invalid arguments given.'
		end
		
		case string1.length <=> string2.length 
			when -1, 0
			str = string1
			when 1
			str = string2
		end
		
		hamming = 0
		
		for i in 0...str.length do
			if string1[i] != string2[i] 
				hamming += 1
			end
		end
		
		hamming	
		
	end


end

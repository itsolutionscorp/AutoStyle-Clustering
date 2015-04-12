def compute(strand1,strand2)
		ham_count = 0	
		min_length_str = 	strand1.length > strand2.length ? strand2 : strand1
		(0...min_length_str.length).each do |i|
			if strand1[i]!= strand2[i]
				ham_count += 1 			
			end
		end	 		
		ham_count	
	end
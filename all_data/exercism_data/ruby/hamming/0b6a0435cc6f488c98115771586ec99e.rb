class Hamming

	
	
	def self.compute(strand1,strand2)
	  ham_count = 0
		if strand1.length > strand2.length
	 		for i in 0...strand2.length do
				if strand1[i]!= strand2[i]
				 ham_count += 1 			
	 			end
	 		end
	 		
	 		ham_count
	 	else
	 		for i in 0...strand1.length do
				if strand1[i]!= strand2[i]
				 ham_count += 1 			
				end 
	 		end
	 		
	 		ham_count
	 	end
	 	
		
	end

	

end

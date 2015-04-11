class Hamming
	
	def Hamming.compute(dna1="",dna2="")
		arrayDna1=dna1.split(//)
		arrayDna2=dna2.split(//)		
		distinct=0;
		# The arrays are the same length
		if arrayDna1.length==arrayDna2.length								
			0.upto(arrayDna1.length-1) { |i| 				
				if arrayDna1[i] != arrayDna2[i] 
					distinct += 1
				end		
			}
		end		
		return distinct
	end

end

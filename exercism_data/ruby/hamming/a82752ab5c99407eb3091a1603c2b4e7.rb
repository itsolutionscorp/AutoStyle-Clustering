class Hamming 
  def self.compute (pdna1, pdna2)
		arrayDna1 = pdna1.split ""
		arrayDna2 = pdna2.split ""
		
		distance = 0
		i = 0
		
		#we walk the shorter ADN array
		if arrayDna1.length <= arrayDna2.length
			for nucleic in arrayDna1
				if nucleic != arrayDna2[i]
					distance += 1
				end
				i += 1 
			end
		else
			for nucleic in arrayDna2
				if nucleic != arrayDna1[i]
					distance += 1
				end
				i += 1 
			end
		end
		
		distance
	end
end

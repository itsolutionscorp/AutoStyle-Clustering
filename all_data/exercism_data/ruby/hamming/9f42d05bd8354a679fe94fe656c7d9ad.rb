class Hamming
	def self.compute(strand1, strand2)
		count = 0
		
		if strand1.length > strand2.length
			shorter = strand2
		else 
			shorter = strand1
		end
		
		if strand1 == strand2
			return count
		else
			for c in 0..shorter.length - 1
				if strand1[c] != strand2[c]
					count +=1
				end
			end
		end
		
		return count

	end

end

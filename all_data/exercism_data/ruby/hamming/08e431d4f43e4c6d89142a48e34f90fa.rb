class Hamming 

	def self.compute(strand1,strand2)
		numDifferences = 0
		i = 0
		[strand1.length,strand2.length].min.times do					
			numDifferences += 1 if strand1[i] != strand2[i]
			i += 1
		end
		return numDifferences
	end
	
end

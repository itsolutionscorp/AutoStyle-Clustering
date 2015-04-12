class Hamming 

	def compute(strand1,strand2)
		numDifferences = 0
		i = 0
		lengthToCompare = [strand1.length,strand2.length].min;
		puts "Length is #{lengthToCompare}"
		lengthToCompare.times do		
			if strand1[i] != strand2[i]
				numDifferences += 1
			end
			i += 1
		end
		return numDifferences
	end
	
end

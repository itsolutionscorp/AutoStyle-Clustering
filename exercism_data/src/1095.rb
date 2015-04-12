def compute(strand1,strand2)
		numDifferences = 0
		[strand1.length,strand2.length].min.times { |i|					
			numDifferences += 1 if strand1[i] != strand2[i]
		}
		return numDifferences
	end
def compute(baseStrand, newStrand)
		maxLengthToCheck = [baseStrand.length, newStrand.length].min
		hamming = 0

		(0..maxLengthToCheck -1).each do |i|
			if (baseStrand[i] != newStrand[i])
				hamming += 1
			end
		end

		hamming
	end
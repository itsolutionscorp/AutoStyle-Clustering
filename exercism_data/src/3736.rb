def compute(firstStrand, secondStrand)
		hammingDistance = 0
		lengthMinStrand = 0
		counter = 0
		if firstStrand.length < secondStrand.length
			lengthMinStrand = firstStrand.length
		else
			lengthMinStrand = secondStrand.length
		end
		while counter < lengthMinStrand
			if firstStrand[counter] != secondStrand[counter]
				hammingDistance += 1
			end
			counter += 1
		end
		return hammingDistance
	end
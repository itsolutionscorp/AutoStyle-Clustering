def compute(strandA, strandB)
		hammingDistance = 0
		index = 0

		strandA.each_char do|i|
  			if i != strandB[index] then
  				hammingDistance += 1
			end
			index += 1
		end

		return hammingDistance
	end
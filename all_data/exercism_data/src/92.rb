def compute(strandA, strandB)
		hammingDistance = 0

		strandA.each_char.with_index do|i, index|
  			if i != strandB[index] then
  				hammingDistance += 1
			end
		end

		hammingDistance
	end
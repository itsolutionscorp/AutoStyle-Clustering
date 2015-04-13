def compute(strandA, strandB)

		if strandA.length == strandB.length
			length = strandA.length
		else
			length = [strandA.length, strandB.length].min
		end

		penalty = 0

		for index in 0..length-1
			cond = strandA[index] == strandB[index]

			if !cond
				penalty += 1
			end
		end

		return penalty
	end
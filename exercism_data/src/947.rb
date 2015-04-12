class Hamming

	def compute(strandA, strandB)
		if strandA == strandB
			0
		else
			differences = 0
			for i in 0..[strandA.length, strandB.length].min - 1 do
				differences += 1 if strandA[i] != strandB[i]
			end
			differences
		end
	end

end

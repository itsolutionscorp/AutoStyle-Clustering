class Hamming
	def Hamming.compute(strandA, strandB)
		strandA = strandA.split(//)
		strandB = strandB.split(//)
		distance = 0

		length = [strandA.length, strandB.length].min - 1
				
		for locus in 0..length
			nucleotideA = strandA[locus]
			nucleotideB = strandB[locus]
			unless nucleotideA == nucleotideB
				distance += 1
			end
		end
		
		distance
	end

end

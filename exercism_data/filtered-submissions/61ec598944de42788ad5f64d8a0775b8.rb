def compute(strandA, strandB)
			distance = 0


			length = strandA.length <= strandB.length ? strandA.length : strandB.length


			0.upto(length-1) do |i|
				distance += 1 unless strandA[i] == strandB[i]
			end

			distance
		end
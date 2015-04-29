def compute(dna1,dna2)
		difcount = 0
		iteratorpos = 0

		dna1string = String.new(dna1)
		dna2string = String.new(dna2)

		dna1string.each_char{ |char|

			if char != dna2string[iteratorpos]
				difcount += 1
			end
			iteratorpos += 1
		}
		return difcount
	end
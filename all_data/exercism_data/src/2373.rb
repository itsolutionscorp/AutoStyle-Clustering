def compute(strandOne, strandTwo)

		d = 0

		strandOne.split('')		#added fore possible refactor	
		strandTwo.split('') 		# ""

		if strandOne[0] != strandTwo[0]
			d = d + 1
		end
		
		if strandOne[1] != strandTwo[1]
			d = d + 1
		end

		if strandOne[2] != strandTwo[2]
			d = d + 1
		end

		if strandOne[3] != strandTwo[3]
			d = d + 1
		end

		if strandOne[4] != strandTwo[4]
			d = d + 1
		end

		if strandOne[5] != strandTwo[5]
			d = d + 1
		end

		if strandOne[6] != strandTwo[6]					
			d = d + 1
		end
	
		if strandOne[7] != strandTwo[7]
			d = d + 1
		end
		
		if strandOne[8] != strandTwo[8]
			d = d + 1
		end

		if strandOne[9] != strandTwo[9]
			d = d + 1
		end

		if strandOne[10] != strandTwo[10]
			d = d + 1
		end
		
		if strandOne[11] != strandTwo[11]
			d = d +1
		end

		return d
			
	end
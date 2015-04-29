def compute(strandOne, strandTwo) #Finds the hamming distance
	
		##
		# First make the strands equal length
		#

		shortenTo = [strandOne.length, strandTwo.length].min - 1 
		strandOne = strandOne[0..shortenTo]
		strandTwo = strandTwo[0..shortenTo]


		##
		# Then determine the length by looping through each string and
		# comparing each pair of letters.

		distance = 0;
		(0..strandOne.length).each do |iii|
			if strandOne[iii] != strandTwo[iii]
				distance += 1
			end
		end
		return distance
	end
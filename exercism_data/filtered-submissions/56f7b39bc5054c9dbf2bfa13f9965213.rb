def compute(strandOne, strandTwo)





		shortenTo = [strandOne.length, strandTwo.length].min - 1
		strandOne = strandOne[0..shortenTo]
		strandTwo = strandTwo[0..shortenTo]






		distance = 0;
		(0..strandOne.length).each do |iii|
			if strandOne[iii] != strandTwo[iii]
				distance += 1
			end
		end
		return distance
	end
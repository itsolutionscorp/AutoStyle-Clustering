def compute(strand1,strand2)
		length, counter = 0, 0
		strand1.each_char.zip(strand2.each_char).each do |a1,a2| 
				if a1 != a2 
					counter += 1
				end
		end
		if strand1.length > strand2.length
				length = strand1.length - strand2.length
		end
		return counter - length
	end
def compute(strand1, strand2)

		if ( strand1.length > strand2.length ) then
			count = strand2.length
		else
			count = strand1.length
		end


		diff = 0
		count.times do |index|

			if ( strand1[index] != strand2[index] ) then
				diff += 1
			end

		end

		return diff

	end
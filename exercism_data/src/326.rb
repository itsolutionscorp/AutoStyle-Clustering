def compute(strand1, strand2)

		if strand1 == strand2
		# If the strings are the same, the Hamming distance is 0.
			0
		else
		# As we compare each character in the string, count each time there is a
		# difference. This will give us our Hamming distance.
			hamming_distance = 0

			# I feel like there should be a cooler way to do this for loop,
			# but I don't know what it is.
			for i in 0..strand1.length - 1

				if strand1[i] != strand2[i]
					hamming_distance += 1
				end

			end

			hamming_distance
		
		end
	end
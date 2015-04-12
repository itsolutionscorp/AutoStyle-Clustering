def compute(s1, s2)
		# If the strings are not of the same length, we only
		# check the number of characters from the smaller one
		if s1.length < s2.length
			numchars = s1.length
		else
			numchars = s2.length
		end

		num_differences = 0
		index = 0
		numchars.times do
			c1 = s1[index]
			c2 = s2[index]

			if c1 != c2
				num_differences += 1
			end
			index += 1
		end	

		return num_differences
	end
def compute(string1, string2)
		arr1 = string1.split
		arr2 = string2.split

		i = 0
		tally = 0

		arr1.each do |letter|

			if letter != arr2[i]
				tally = tally + 1
			end

			i = i + 1

		end

		return tally
	end
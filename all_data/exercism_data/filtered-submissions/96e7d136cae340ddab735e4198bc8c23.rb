def compute(string1, string2)
		arr1 = string1.split('')
		arr2 = string2.split('')

		i = 0
		tally = 0

		if arr1.length <= arr2.length
			arr1.each.with_index do |letter, i|
				if letter != arr2[i]
					tally = tally + 1
				end
			end
	    else
	    	arr2.each.with_index do |letter, i|
				if letter != arr1[i]
					tally = tally + 1
				end
			end
		end

		return tally
	end
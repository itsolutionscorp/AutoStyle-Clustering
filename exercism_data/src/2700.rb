def compute(str1, str2)	
		str1_array = str1.split("")
		str2_array = str2.split("")
		short_array = str1_array
		if str1_array.length > str2_array.length
			short_array = str2_array
		end
		score = 0
		short_array.each_index do |n|
			unless str1_array[n] == str2_array[n]
				score += 1
			end
		end
		return score
	end
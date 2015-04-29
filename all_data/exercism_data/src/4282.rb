def compute(str1, str2)
		result = 0
		len_diff = (str1.length - str2.length).abs
		iter = [str1.length, str2.length].min
		(0..(iter-1)).each do |i|
			if str1[i] != str2[i]
				result += 1
			end
		end
		return result + len_diff
	end
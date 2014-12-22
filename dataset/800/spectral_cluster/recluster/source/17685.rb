def toSortArr(str)
	str.downcase.split(//).sort()
end

def combine_anagrams(words)
	result = []

	until words.empty? do
		new_arr = [] << words[0]
		
		for i in 1..words.length - 1
			if (toSortArr(new_arr[0]) == toSortArr(words[i]))
				new_arr << words[i]
			end
		end
		
		result << new_arr

		words = words - new_arr
	end

	result
end
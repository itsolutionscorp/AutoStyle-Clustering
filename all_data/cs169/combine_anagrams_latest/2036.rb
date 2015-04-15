def combine_anagrams(words)
	anagram_groups = []
	anagram_group_nums = {}
	i=0
	words.each do |x|
		char_array = []
		b = 0
		x.each_char do
			|c| char_array[b] = c.downcase
			b+=1 
		end
		x_key = char_array.sort

		if anagram_group_nums.has_key? x_key
			anagram_groups[anagram_group_nums[x_key]] << x
		else
			anagram_group_nums[x_key] = i
			i+=1
			anagram_groups << [x] 
		end
	end
	return anagram_groups
end


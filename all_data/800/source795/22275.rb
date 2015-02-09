def combine_anagrams(words)
	lets_sorted = []
	wordy_st = ""
	hashy = {}
	anagrams = []
	first_case = true
	words.each{|x| wordy_st = x.downcase.chars.sort.join
					lets_sorted << wordy_st 
					wordy_st = ""}
	lets_sorted.each_index{|a| key_a = lets_sorted[a]
								if !hashy.has_key?(key_a)
									hashy[key_a] = [words[a]]
								else
									hashy[key_a] = hashy[key_a] << words[a]
								end
								}
	hashy.each{|key, value| anagrams << value}
	anagrams
end
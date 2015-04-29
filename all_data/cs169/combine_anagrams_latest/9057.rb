def combine_anagrams(words)
	tempMap = {}
	
	words.each do |word|
		key = word.downcase.chars.sort{ |a, b| a.casecmp(b) }.join
		
		if tempMap[key] == nil
			tempMap[key] = [word]
		else
			tempMap[key] << word
		end						
	end
	
	result_array = []
	
	tempMap.each_value do |combined_anagram|
		result_array << combined_anagram
	end
	
	result_array
end
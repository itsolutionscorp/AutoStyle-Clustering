def combine_anagrams(words)
	help_hash = Hash.new(0)
	words.each do |word|
		key = word.downcase.chars.sort.join	
		if help_hash.has_key?(key)
			help_hash[key] << word
		else
			help_hash[key] = [word]
		end
	end
  #words.collect!{|word| word.downcase.chars.sort.join}
  return help_hash.values
end
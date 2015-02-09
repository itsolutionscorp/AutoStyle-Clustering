def order_letters_alphabetically(str)
	str.chars.sort { |a, b| a.casecmp(b) }.join
end

def combine_anagrams(words)
	groups_hash = Hash.new
	words.each do |word|
		ordered_word = order_letters_alphabetically word
		if groups_hash.has_key? ordered_word
			groups_hash[ordered_word].push word
		else
			groups_hash[ordered_word] = Array.new
			groups_hash[ordered_word].push word
		end
	end
	groups = Array.new
	groups_hash.each{|key,value| groups << value }
	groups
end

#check combine anagrams
combine_anagrams(['cars','for','potatoes','racs','four','scar','creams','scream']).each do |g| 
	puts "group #{g.class} has #{g.size} element(s)"
	puts g
end
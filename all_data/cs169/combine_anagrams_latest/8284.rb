# input:
#  ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#  ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
	return [] if words.empty?
	
	return words.group_by { |w| w.to_s.downcase.chars.sort }.values.to_a
end

#puts "input ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']"
#puts combine_anagrams(['carS', 'for', 'potatoes', 'rAcs', 'four','scar', 'cReams', 'scrEam']).inspect
#puts "\n"
#puts combine_anagrams(['cars', 'for', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
#puts 'expected [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]'

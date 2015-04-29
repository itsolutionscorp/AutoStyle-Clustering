# input:
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
# <YOUR CODE HERE>
	#@words_hash = words.each_with_object(Hash.new []) do |word, hash|
  #	hash[word.chars.sort] += [word]
	#end
	anagrams = words.group_by { |word| word.downcase.chars.sort }.values
	#@words_hash.each {|key, value| puts "#{key} is #{value}"}
	#return @words_hash.values
	#puts anagrams.to_s
	return anagrams
end

palabro=['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
combine_anagrams(palabro)
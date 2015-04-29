# input:
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  hash = {}
  words.each do |word|
    if not hash.has_key? word.downcase.chars.sort.join
		hash[word.downcase.chars.sort.join]= []
	end
    hash[word.downcase.chars.sort.join].push word
  end
 hash.values
end

print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])


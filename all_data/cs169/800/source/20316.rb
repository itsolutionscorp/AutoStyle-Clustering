# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
	result = {}
	words.each{|word| result[word.downcase.chars.sort.join] ||= []; result[word.downcase.chars.sort.join] << word}
	result.values
end

def test1()
	combine_anagrams(['cArs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
end


def combine_anagrams(words)
  words_hash={}
  for word in words
    if words_hash[word.downcase.chars.sort.join]==nil
		words_hash[word.downcase.chars.sort.join]=[]
	end
	words_hash[word.downcase.chars.sort.join] << word
  end
  return words_hash.values
end
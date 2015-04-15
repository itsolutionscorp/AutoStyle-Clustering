def combine_anagrams(words)

  num_words = words.count()
  w = 0
  
  anagrams = {}
  
  while w < num_words
    word = words[w]
    word_array = word.chars.to_a.sort
    len = word_array.count()
    i = 0
    sorted_word = ""
    while i < len
      sorted_word = sorted_word + word_array[i]
	  i = i + 1
    end
	w = w + 1
    
	if anagrams[sorted_word] == nil
	  anagrams[sorted_word] = []
	end
	anagrams[sorted_word] =  anagrams[sorted_word] + [word]
  end
  
  array_anagrams = []

  anagrams.each_value {|value| array_anagrams << value}
  
  return array_anagrams
end
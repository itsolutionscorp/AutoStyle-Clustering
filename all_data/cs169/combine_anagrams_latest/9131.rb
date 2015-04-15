def combine_anagrams(words)
  dictionary = Hash.new
  words.each do |word|
    sorted_word = word.downcase.chars.to_a.sort.join
    if (!dictionary[sorted_word])
      dictionary[sorted_word] = [word]
    else  
      dictionary[sorted_word] << word
    end
  end
  
  anagrams = []
  dictionary.each_value do |value|
    anagrams << value
  end
  anagrams
end
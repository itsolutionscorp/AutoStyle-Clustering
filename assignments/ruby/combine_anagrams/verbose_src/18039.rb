def combine_anagrams(words) 
  words_hash = Hash.new([])
  words.each do |word|
    words_hash[word.downcase.chars.sort] += [word]
  end
  words_array = []
  words_hash.each do |key, value| 
    words_array << value
  end  
  words_array
end


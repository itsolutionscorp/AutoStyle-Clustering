def combine_anagrams(words)
  hash = {}
  words.each do |word|
    word_char_nocase = word.downcase.chars.sort
    if hash[word_char_nocase] == nil
      hash[word_char_nocase] = [word]
    elsif
      hash[word_char_nocase] << word      
    end
  end
  
  return hash.values
end
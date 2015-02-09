def combine_anagrams(words)
  anagram_hash = Hash.new
  words.each do |word|
    char_array = word.scan(/./).sort
    anagram_key = char_array.to_s 
    list = anagram_hash[anagram_key]
    unless list.nil?
      list << word
    else
      list = [word]
      anagram_hash.store(anagram_key,list)
    end 
  end
  return anagram_hash.values
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
combine_anagrams words

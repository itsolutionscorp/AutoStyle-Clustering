def combine_anagrams(words)
  anagram_hash = Hash.new
  words.each do |word|
    char_array = word.scan(/./).sort
    anagram_key = char_array.to_s
    list = anagram_hash[anagram_key]
    if list.nil? then
      list = [word]
      anagram_hash.store(anagram_key, list)
    else
      (list << word)
    end
  end
  return anagram_hash.values
end
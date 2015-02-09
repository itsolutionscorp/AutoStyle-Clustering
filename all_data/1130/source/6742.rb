def combine_anagrams(words)
  anagram_hash = Hash.new
  words.each do |word|
    anagram = word.downcase.chars.sort
    if anagram_hash.key?(anagram)
      anagram_hash[anagram] << word
    else
      anagram_hash[anagram] = Array[word]
    end
  end
  anagram_hash.values
end

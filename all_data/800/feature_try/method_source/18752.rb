def combine_anagrams(words)
  word_hash = Hash.new
  words.each do |word|
    anagram = get_anagram(word)
    if word_hash.key?(anagram) then
      (word_hash[anagram] << word)
    else
      word_hash[anagram] = [word]
    end
  end
  words_anagram = []
  word_hash.each { |key, word| words_anagram = (words_anagram + [word]) }
  return words_anagram
end
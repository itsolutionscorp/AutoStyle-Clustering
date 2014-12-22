def combine_anagrams(words)
  words_hash = {}
  words.each do |word|
    word_key = word.split("").sort.join
    words_hash[word_key] = (words_hash[word_key] or [])
    words_hash[word_key].push(word)
  end
  words_hash.values
end


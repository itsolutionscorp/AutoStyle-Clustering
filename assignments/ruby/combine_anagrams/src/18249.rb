def combine_anagrams(words)
  hash = {}
  words.each do |word|
    normalized_word = word.downcase.split("").sort.join
    puts(normalized_word)
    if hash.has_key?(normalized_word) then
      hash.store(normalized_word, hash.fetch(normalized_word).push(word))
    else
      hash.store(normalized_word, Array.new(1, word))
    end
  end
  if (hash.size == 1) then
    return words
  else
    return hash.values
  end
end
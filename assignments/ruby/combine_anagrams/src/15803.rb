def combine_anagrams(words)
  hash = Hash.new { |hash, key| hash[key] = [] }
  words.each do |word|
    sorted_word = word.downcase.scan(/./).sort.to_s
    value = hash[sorted_word]
    (value << word)
  end
  hash.values
end
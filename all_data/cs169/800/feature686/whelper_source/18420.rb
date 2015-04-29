def combine_anagrams(words)
  sorted_hash = Hash.new
  words.each do |word|
    sorted_word = word.downcase.split("").sort.join
    if sorted_hash.has_key?(sorted_word) then
      sorted_hash.store(sorted_word, sorted_hash.fetch(sorted_word).push(word))
    else
      sorted_hash.store(sorted_word, Array.new(1, word))
    end
  end
  return sorted_hash.values
end


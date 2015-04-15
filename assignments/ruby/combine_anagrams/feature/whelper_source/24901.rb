def combine_anagrams(words)
  word_hash = {}
  words.each do |w|
    sorted_word = w.downcase.chars.sort.join
    if (not word_hash.has_key?(sorted_word)) then
      word_hash[sorted_word] = [w]
    else
      (word_hash[sorted_word] << w)
    end
  end
  return word_hash.values
end


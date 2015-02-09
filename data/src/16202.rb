def combine_anagrams(words)
  hash = Hash.new(0)
  words.each do |word|
    sorted_downcased_word = word.downcase.chars.sort.to_a.join
    if hash.has_key?(sorted_downcased_word) then
      hash[sorted_downcased_word] = hash[sorted_downcased_word].push(word)
    else
      hash[sorted_downcased_word] = Array.new(1, word)
    end
  end
  return hash.values
end
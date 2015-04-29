def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    sorted_word = word.downcase.split("").sort.join
    if anagrams.has_key?(sorted_word) then
      anagrams[sorted_word].push(word)
    else
      anagrams[sorted_word] = Array.new(1, word)
    end
  end
  return anagrams.values
end
def combine_anagrams(words)
  anagrams = Hash.new
  words.each { |w| \
    sorted_word = w.downcase.chars.sort.join
    if anagrams.has_key?(sorted_word)
      anagrams[sorted_word] << w
    else
      anagrams[sorted_word] = [w]
    end
  }
  anagrams.values
end

def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |w|
    sorted_word = w.downcase.chars.sort.join
    if anagrams.has_key?(sorted_word) then
      (anagrams[sorted_word] << w)
    else
      anagrams[sorted_word] = [w]
    end
  end
  anagrams.values
end
def combine_anagrams(words)
  anagrams = {}
  words.each do |w|
    x = w.downcase.chars.sort.join
    if anagrams.has_key? x
      anagrams[x] << w
    else
      anagrams[x] = [w]
    end
  end
  anagrams.values
end

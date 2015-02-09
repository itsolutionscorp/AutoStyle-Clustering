def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    chars = word.downcase.chars.sort.join
    if anagrams[chars]
      anagrams[chars] << word
    else
      anagrams[chars] = [word]
    end
  end
  anagramsList = anagrams.map { |key,value| value }

end


def combine_anagrams(words)
  anagrams = {}

  words.each do |word|
    key = word.downcase.split(%r//).sort.join
    if anagrams.include? key then
      anagrams[key] << word
    else
      anagrams[key] = [word]
    end
  end

  anagrams.values
end

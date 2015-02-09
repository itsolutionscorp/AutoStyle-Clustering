def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    if anagrams[word.downcase.chars.sort.join].nil? then
      anagrams[word.downcase.chars.sort.join] = [word]
    else
      anagrams[word.downcase.chars.sort.join] += [word]
    end
  end
  anagrams.values.to_a
end


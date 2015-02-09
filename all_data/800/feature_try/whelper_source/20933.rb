def combine_anagrams(words)
  return [] if (words == [])
  array_of_anagrams = [[words[0]]]
  words = words.drop(1)
  words.each do |word|
    found = 0
    word_clean = word.downcase.chars.sort.join
    array_of_anagrams.each do |line_of_anagrams|
      if (line_of_anagrams[0].downcase.chars.sort.join == word_clean) then
        line_of_anagrams.push(word)
        found = 1
      end
    end
    array_of_anagrams.push([word]) if (found == 0)
  end
  return array_of_anagrams
end


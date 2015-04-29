def combine_anagrams(words)
  response = []
  matched = []
  index = 0
  words.each do |anagram|
    if (!matched.include?(anagram))
      matched << anagram
      anagram_group = []
      anagram_group << anagram
      (index + 1).upto(words.length - 1) do |i|
        if (anagram.downcase.chars.sort.join == words[i].downcase.chars.sort.join)
          anagram_group << words[i]
          if (!matched.include?(words[i]))
            matched << words[i]
          end
        end
      end
      response << anagram_group
    end
    index += 1
  end
  response
end
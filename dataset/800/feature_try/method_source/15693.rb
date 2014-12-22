def combine_anagrams(words)
  words if (words == [])
  anagram_groups = {}
  words.each do |word|
    sortedWord = word.downcase.chars.sort.join
    if anagram_groups[sortedWord] then
      anagram_groups[sortedWord].push(word)
    else
      words = []
      words.push(word)
      anagram_groups[sortedWord] = words
    end
  end
  return hash_to_array(anagram_groups)
end
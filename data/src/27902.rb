def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    sortedWord = word.chars.sort { |a, b| a.casecmp(b) }.join.downcase
    if (hash[sortedWord] == nil) then
      newSet = Set.new
      newSet.add(word)
      hash.store(sortedWord, newSet)
    else
      hash.store(sortedWord, hash[sortedWord].add(word))
    end
  end
  return hash
end
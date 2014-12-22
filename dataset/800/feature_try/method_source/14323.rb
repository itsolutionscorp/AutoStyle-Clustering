def combine_anagrams(words)
  def are_anagrams?(word1, word2)
    return (word1.downcase.split("").sort == word2.downcase.split("").sort)
  end
  groups = []
  words.each do |word|
    group = groups.find { |group| are_anagrams?(group[0], word) }
    group ? ((group << word)) : ((groups << [word]))
  end
  return groups
end
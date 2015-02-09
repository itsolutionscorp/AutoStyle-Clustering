def combine_anagrams(words)
  groups = Hash.new
  words.map do |word|
    sortedWord = word.downcase.chars.sort.join
    if groups[sortedWord] == nil
      groups[sortedWord] = []
    end
    groups[sortedWord] << word
  end
  anagrams = []
  i = 0
  groups.each_key do |key|
    anagrams[i] = groups[key]
    i += 1
  end
  return anagrams
end
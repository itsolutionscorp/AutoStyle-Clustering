def combine_anagrams(words)
  groups = {}
  words.each do |word|
    letters = word.downcase.split("").sort.join("")
    groups[letters] = [] if (not groups[letters])
    groups[letters].push(word)
  end
  return groups.values
end
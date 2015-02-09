def combine_anagrams(words)
  groups = {}
  words.each do |word|
    key = word.downcase.chars.sort.join
    groups[key] = groups.fetch(key, []).push(word)
  end
  return groups.values
end
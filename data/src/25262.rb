def combine_anagrams(words)
  anagramGroups = {}
  words.each do |word|
    key = word.downcase.split("").sort.join
    anagramGroups[key] ||= []
    (anagramGroups[key] << word)
  end
  return anagramGroups.values
end
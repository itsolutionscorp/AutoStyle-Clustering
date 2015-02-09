def combine_anagrams(words)
  groups = {}
  words.each do |word|
    key = word.downcase.chars.sort.join
    (not groups[key]) ? (groups[key] = [word]) : ((groups[key] << word))
  end
  groups.values
end
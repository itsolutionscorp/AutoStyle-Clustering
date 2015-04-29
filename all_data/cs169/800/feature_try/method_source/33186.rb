def combine_anagrams(words)
  groups = []
  words.each { |w| add_to_group(groups, w) }
  return groups
end
def combine_anagrams(words)
  return [] if words.empty?
  groups = {}
  groups.default = []
  words.each { |x| groups[x.downcase.split("").sort.join] += [x] }
  return groups.values
end


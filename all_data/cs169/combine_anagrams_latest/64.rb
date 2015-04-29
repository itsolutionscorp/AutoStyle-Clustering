def combine_anagrams(words)
  # Hash that will store prepared standards for comparison
  # as keys and lists of matched anagrams as values
  groups = Hash.new

  words.each do |word|
    standard = word.downcase.split(//).sort.join
    groups[standard] = groups[standard].to_a + [word]
  end

  return groups.values
end

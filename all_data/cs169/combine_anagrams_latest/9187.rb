def combine_anagrams(words)
  unique = words.map do |word|
    word.downcase.chars.sort.join
  end.uniq
  
  groups = []
  unique.length.times { groups << [] }
  # groups.fill([], 0, unique.length) # This give different result than previous line
  words.each do |word|
    groups[unique.index(word.downcase.chars.sort.join)].push word
  end
  groups
end

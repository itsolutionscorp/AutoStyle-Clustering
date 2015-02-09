def combine_anagrams(words)
  raise(NoElementsInArrayError) unless (words.length > 0)
  groups = {}
  words.each do |word|
    chars = word.downcase.chars.sort.join
    groups[chars] ||= []
    (groups[chars] << word)
  end
  groups.values
end
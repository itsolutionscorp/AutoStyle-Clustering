def combine_anagrams(words)
  groups = Hash.new
  words.each do |word|
    key = word.downcase.chars.sort.join
    groups[key] = Array.new if (not groups.key?(key))
    (groups[key] << word)
  end
  return groups.values
end


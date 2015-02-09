#returns an array of groups of the words that are anagrams
def combine_anagrams(words)
  #for each word, first get the canonical (sorted) word for it
  #then add it to the group of words stored for the canonical
  group_map = {}
  words.each do |w|
    canonical = w.downcase.chars.sort.join
    if group_map.include?(canonical)
      group_map[canonical] = group_map[canonical] << w
    else
      group_map[canonical] = [w]
    end
  end
  group_map.values
end

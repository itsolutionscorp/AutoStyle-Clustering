def combine_anagrams(words)
  words.each.with_object({}) do |item, groups|
    item_anagram = item.downcase.chars.sort.join
    (groups[item_anagram] << item) if groups.include?(item_anagram)
    groups[item_anagram] = [item] unless groups.include?(item_anagram)
  end.values
end


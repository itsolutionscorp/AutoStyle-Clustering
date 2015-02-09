def combine_anagrams(words)
  groups = Hash.new
  words.each do |word|
    group = word.downcase.chars.sort.join
    if groups.has_key?(group) then
      groups.store(group, groups[group].concat([word]))
    else
      groups.store(group, [word])
    end
  end
  return groups.values
end
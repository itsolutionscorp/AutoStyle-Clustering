def combine_anagrams(words)
  groups = Hash.new
  words.each do |w|
    group_key = w.downcase.chars.sort.join
    if groups.has_key?(group_key)
      groups[group_key] << w
    else
      groups[group_key] = [w]
    end
  end
  return groups.values
end

def combine_anagrams(words)
  groups = Hash.new()
  words.each do |w|
    key = w.downcase.chars.sort.join
    if !groups.has_key?(key)
      groups[key] = []
    end
    groups[key] += [w]
  end
  return groups.values
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s
def combine_anagrams(words)
  groups = Hash.new
  words.each do |word|
    key = word.downcase.chars.sort.join
    if !groups.key?(key)
      groups[key] = Array.new
    end
    groups[key] << word
  end

  return groups.values
end

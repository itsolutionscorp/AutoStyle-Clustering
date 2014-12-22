def combine_anagrams(words)
  groups = Hash.new
  words.each do |word|
    sortWord = word.downcase
    sorted = sortWord.chars.sort.to_s
    if groups.has_key?(sorted) then
      (groups[sorted] << [word])
    else
      groups[sorted] = [word]
    end
  end
  filteredGroups = []
  groups.each { |key, value| (filteredGroups << value.flatten) }
  return filteredGroups
end


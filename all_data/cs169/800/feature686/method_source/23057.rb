def combine_anagrams(words)
  anagramGroups = Array.new
  words.each do |x|
    anagramGroup = Array.new
    words.each do |y|
      if (x.downcase.chars.sort.join == y.downcase.chars.sort.join) then
        anagramGroup.push(y)
      end
    end
    if (not anagramGroup.empty?) and (not anagramGroups.include?(anagramGroup)) then
      anagramGroups.push(anagramGroup)
    end
  end
  return anagramGroups
end
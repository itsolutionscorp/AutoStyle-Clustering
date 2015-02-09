def combine_anagrams(words)  
  anagramGroups = Array.new
  words.each do |x|
    anagramGroup = Array.new
    words.each do |y|
      if x.downcase.chars.sort.join == y.downcase.chars.sort.join
        anagramGroup.push(y)
      end
    end
    if !anagramGroup.empty? and !anagramGroups.include? anagramGroup
      anagramGroups.push(anagramGroup)
    end
  end
  return anagramGroups
end


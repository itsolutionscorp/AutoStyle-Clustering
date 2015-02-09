def combine_anagrams(words)
  tempHash = Hash.new
  arrayOfGroups = []
  arrayOfGroupsHash = Hash.new
  words.each do |word|
    sortedWord = word.chars.sort { |a, b| a.casecmp(b) }.join
    tempHash[word] = sortedWord
  end
  tempHash.each do |key, value|
    tempArray = []
    words.each do |word|
      if (word.chars.sort { |a, b| a.casecmp(b) }.join.downcase == value.downcase) then
        tempArray.push(word)
      end
    end
    arrayOfGroupsHash[value.downcase] = tempArray if (tempArray.length != 0)
  end
  arrayOfGroupsHash.each { |key, value| arrayOfGroups.push(value) }
  return arrayOfGroups
end


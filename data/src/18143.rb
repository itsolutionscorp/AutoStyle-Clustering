def combine_anagrams(words)
  anagramGroups = Hash.new
  words.collect do |word|
    wordAnagram = word.downcase.chars.sort
    if anagramGroups.has_key?(wordAnagram) then
      (anagramGroups[wordAnagram] << word)
    else
      anagramGroups[wordAnagram] = [word]
    end
  end
  anagramGroups.values
end
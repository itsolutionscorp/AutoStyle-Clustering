def combine_anagrams(words)
  response = []
  matched = []
  index = 0
  words.each do |anagram|
    if (not matched.include?(anagram)) then
      (matched << anagram)
      anagram_group = []
      (anagram_group << anagram)
      (index + 1).upto((words.length - 1)) do |i|
        if (anagram.downcase.chars.sort.join == words[i].downcase.chars.sort.join) then
          (anagram_group << words[i])
          (matched << words[i]) if (not matched.include?(words[i]))
        end
      end
      (response << anagram_group)
    end
    index = (index + 1)
  end
  response
end


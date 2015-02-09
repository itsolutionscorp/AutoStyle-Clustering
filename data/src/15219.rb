def combine_anagrams(words)
  groups = Hash.new
  words.each do |word|
    anagram = word.downcase.split("").sort.join
    if groups.include?(anagram) then
      (groups[anagram] << word)
    else
      groups[anagram] = [word]
    end
  end
  groups.values
end
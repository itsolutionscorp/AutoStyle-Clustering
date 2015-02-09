def combine_anagrams(words)
  anagram_group = []
  words.each do |word|
    in_anagram = false
    anagram_group.each_index do |index|
      if (anagram_group[index][0].downcase.split("").sort == word.downcase.split("").sort) then
        anagram_group[index].push(word)
        in_anagram = true
      end
    end
    anagram_group.push([word]) if (not in_anagram)
  end
  anagram_group
end


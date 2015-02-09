def combine_anagrams(words)
  anagram_groups = []
  words.each do |word|
    pp(word)
    if (anagram_groups.length == 0) then
      anagram_groups.push([word])
      next
    end
    is_word_added = false
    anagram_groups.each do |group|
      if group.is_a?(Array) and (group.first.length == word.length) then
        if (group.first.downcase.chars.sort == word.downcase.chars.sort) then
          group.push(word)
          is_word_added = true
          break
        end
      end
    end
    anagram_groups.push([word]) unless is_word_added
  end
  pp(anagram_groups)
end


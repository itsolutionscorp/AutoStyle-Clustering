def combine_anagrams(words)
  anagrams_group = []
  return anagrams_group if words.empty?
  anagrams_group = [[words[0]]]
  words.delete_at(0)
  words.each do |word|
    flag_added = false
    word_array = word.downcase.split(//).sort
    anagrams_group.each do |group|
      if ((word_array <=> group[0].downcase.split(//).sort) == 0) then
        (group << word)
        flag_added = true
        break
      end
    end
    (anagrams_group << [word]) if (not flag_added)
  end
  return anagrams_group
end


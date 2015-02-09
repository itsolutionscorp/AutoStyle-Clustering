def combine_anagrams(list_of_words)
  result = []
  list_of_words.each do |word|
    found = false
    for added_words in result do
      if word.anagrams?(added_words[0]) then
        (added_words << word)
        puts("==>#{added_words}")
        found = true
        break
      end
    end
    (result << [word]) if (not found)
  end
  return result
end
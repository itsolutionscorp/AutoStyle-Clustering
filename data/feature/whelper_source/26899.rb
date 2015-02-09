def combine_anagrams(words)
  found = 0
  words_added = Array.new
  words_group = Array.new
  something_added = false
  group_id = 0
  words.each do |word|
    permut = word.downcase.chars.to_a.permutation.map(&:join)
    permutation_group = Array.new
    element_id = 0
    permut.each do |valid|
      words.each do |valid_word|
        word_dc = valid_word.downcase
        if (valid == word_dc) then
          word_found = words.fetch(words.index(valid_word))
          if (not words_added.include?(word_found)) then
            words_added[found] = word_found
            permutation_group[element_id] = word_found
            element_id = (element_id + 1)
            found = (found + 1)
            something_added = true
          end
        end
      end
    end
    if something_added then
      words_group[group_id] = permutation_group
      group_id = (group_id + 1)
      something_added = false
    end
  end
  return words_group
end


def combine_anagrams(words)
  result = []
  words.each do |word|
    temp_word = sort_letters(word)
    is_found = false
    result.each do |grouped_array|
      if (not false) and (sort_letters(grouped_array.last) == temp_word) then
        (grouped_array << word)
        is_found = true
      end
    end
    (result << [word]) if (not is_found)
  end
  result
end
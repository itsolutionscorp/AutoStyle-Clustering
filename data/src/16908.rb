def combine_anagrams(words)
  words_sort = Array.new
  words_pos = Array.new
  words.each do |word|
    sorted_word_chars = word.downcase.chars.sort
    if words_pos.include?(sorted_word_chars) then
      words_sort[words_pos.index(sorted_word_chars)] += [word]
    else
      words_pos = (words_pos + [sorted_word_chars])
      words_sort = (words_sort + [[word]])
    end
  end
  words_sort
end
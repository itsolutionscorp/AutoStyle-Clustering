def combine_anagrams(words)
  loopit = (words == []) ? (false) : (true)
  sorted_words = []
  while loopit do
    current_loop = []
    current_word_sorted = words.first.downcase.chars.sort.join
    words.each do |word|
      if (current_word_sorted == word.downcase.chars.sort.join) then
        (current_loop << word)
      end
    end
    (sorted_words << current_loop)
    words = (words - current_loop)
    loopit = false if (words == [])
  end
  return sorted_words.sort
end


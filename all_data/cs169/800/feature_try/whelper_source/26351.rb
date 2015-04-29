def combine_anagrams(original_words)
  words_with_ordered_letters = []
  original_words.each do |word|
    words_with_ordered_letters.push(word.downcase.chars.sort.join)
  end
  anagram_list = []
  anagram = []
  pos = 0
  while (not words_with_ordered_letters.empty?) do
    original_word = original_words.fetch(pos)
    ordered_word = original_word.downcase.chars.sort.join
    delay = 0
    while words_with_ordered_letters.include?(ordered_word) do
      position = words_with_ordered_letters.index(ordered_word)
      anagram.push(original_words.fetch(position))
      words_with_ordered_letters.delete_at(postion)
      delay = (delay + 1)
    end
    pos = (pos + 1)
  end
  anagram_list.push(anagram)
  anagram.clear
  anagram_list
end


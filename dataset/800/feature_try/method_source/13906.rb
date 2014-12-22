def combine_anagrams(words)
  word_group = {}
  output_word_groups = []
  words.each do |word|
    sorted_alpha = word.downcase.split("").sort.join
    if (word_group[sorted_alpha] == nil) then
      word_group[sorted_alpha] = Array[word]
    else
      word_group[sorted_alpha].push(word)
    end
  end
  word_group.each { |key, value| output_word_groups.push(value) }
  output_word_groups
end
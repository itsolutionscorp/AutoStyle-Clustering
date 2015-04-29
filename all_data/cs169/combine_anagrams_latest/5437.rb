def combine_anagrams(words)
  word_hash = {}
  final_array = []
  i = 0
  words.each do |word|
    ordered_word = word.downcase.chars.sort.join
    if !word_hash.has_key?(ordered_word)
      word_hash.store(ordered_word, i)
      final_array.push([word])
      i += 1
    else
      final_array[word_hash.fetch(ordered_word)].push(word)
    end
  end
  return final_array
end

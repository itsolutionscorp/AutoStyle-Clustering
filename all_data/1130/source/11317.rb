def combine_anagrams(words)
  word_groups = {}
  words.each do |word|
    key = word.downcase.chars.sort.join
    if (word_groups.has_key?(key))
      word_groups[key].push(word)
    else
      word_groups[key] = []
      word_groups[key].push(word)
    end
  end
  return word_groups.values
end
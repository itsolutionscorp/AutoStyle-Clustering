def combine_anagrams(words)
  anagrams = []
  map = Hash.new
  for original_word in words do
    (sorted_word = original_word.downcase.chars.sort.join
    puts(sorted_word)
    map[sorted_word] = [] if (not map.has_key?(sorted_word))
    map[sorted_word] = map[sorted_word].push(original_word))
  end
  count = 0
  for key in map.keys do
    (anagrams[count] = map.values_at(key)[0]
    count = (count + 1))
  end
  return anagrams
end


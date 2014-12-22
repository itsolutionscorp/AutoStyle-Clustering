def combine_anagrams(words)
  sorted_words = {}
  words.each_index do |i|
    key = words[i].downcase.chars.sort.join
    sorted_words[key] = [] if (sorted_words[key] == nil)
    sorted_words[key] += [words[i]]
  end
  return sorted_words.values
end


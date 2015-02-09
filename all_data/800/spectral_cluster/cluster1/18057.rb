def combine_anagrams(words)
  sorted_words = {}
  words.each_index {|i|
    key = words[i].downcase.chars.sort.join
    if sorted_words[key] == nil
      sorted_words[key] = []
    end
    sorted_words[key] += [words[i]]
  }
  return sorted_words.values
end

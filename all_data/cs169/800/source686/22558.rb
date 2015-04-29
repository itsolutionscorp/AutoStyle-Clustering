def combine_anagrams(words)
  sorted_words = []
  i = 0
  words.each do |word|
    sorted_words << word
    sorted_words[i] = sorted_words[i].downcase()
    sorted_words[i] = sorted_words[i].chars.sort_by(&:downcase).join
    i = i+1
  end

  anagrams_list = []
  sorted_words.each do |word|
    unless anagrams_list.include?(word)
      anagrams_list << word
    end
  end
  i = 0
  final_list = []
  for anagram in anagrams_list
    j = 0
    final_list[i] = []
    for word in sorted_words
      if anagram == word
        final_list[i] << words[j]
      end
    j = j+1
    end
  i = i+1
  end

  return final_list

end

#puts combine_anagrams(['caRs', 'For', 'potatoes', 'rAcs', 'four','scar', 'creams', 'scream'])


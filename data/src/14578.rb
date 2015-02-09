def combine_anagrams(words)
  result = {}
  words.each do |word|
    w_sorted = word.downcase.chars.sort.join
    next unless result[w_sorted].nil?
    result[w_sorted] = [word]
    ((words.index(word) + 1)..(words.count - 1)).each do |i|
      word2 = words[i]
      word2_sorted = word2.downcase.chars.sort.join
      (result[w_sorted] << word2) if (w_sorted == word2_sorted)
    end
  end
  result.values
end
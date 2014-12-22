def combine_anagrams(words)
  sorted = Array.new
  words.each do |word|
    match = false
    sorted.each do |w|
      if sort_compare_words(word, w[0]) then
        w[w.count] = word
        match = true
      end
    end
    if (not match) then
      sorted[sorted.count] = Array.new
      sorted[(sorted.count - 1)][0] = word
    end
  end
  return sorted
end
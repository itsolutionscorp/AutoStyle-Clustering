def combine_anagrams(words)
  anagrams = Array.new
  sorts = Array.new
  words.each do |word|
    sorted = word.downcase.chars.sort.join
    if sorts.include? sorted
      anagrams[sorts.index(sorted)] << word
    else
      sorts << sorted
      anagrams << Array.new(1, word)
    end
  end
  return anagrams
end

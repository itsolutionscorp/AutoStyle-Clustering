def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    sortedWord = Array.new
    word.chars { |char| (sortedWord << char) }
    key = sortedWord.sort.join
    key.lower!
    anagrams[key] = Array.new if (not anagrams[key])
    (anagrams[key] << word)
  end
  returnArray = Array.new
  anagrams.each { |x, y| (returnArray << y) }
  return returnArray
end
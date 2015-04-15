def combine_anagrams(words)
  wordsHash = Hash.new { |h, k| h[k] = [] }
  i = 0
  words.each do |word|
    wordsHash[word.downcase.chars.sort.join] = wordsHash[word.downcase.chars.sort.join].push(word)
  end
  returnArray = []
  wordsHash.keys.each { |k| (returnArray << wordsHash[k]) }
  returnArray
end
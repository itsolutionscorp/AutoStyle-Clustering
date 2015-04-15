def combine_anagrams(words)
  hashWordList = Hash.new { |hash, key| hash[key] = Array.new }
  words.each { |word| (hashWordList[word.downcase.chars.sort.join] << word) }
  hashWordList.values
end


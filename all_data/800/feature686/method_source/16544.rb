def combine_anagrams(words)
  h = Hash.new
  words.collect do |word|
    wordArr = word.downcase.split(//)
    sortedWord = wordArr.sort.join
    h[sortedWord] ? (h[sortedWord].push(word)) : (h[sortedWord] = [word])
  end
  h.values
end
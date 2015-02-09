def combine_anagrams(words)
  hWords = Hash.new { |h, k| h[k] = [] }
  words.each { |w| (hWords[w.downcase.chars.sort.join] << w) }
  aWords = []
  @count = 0
  hWords.each { |key| (aWords << hWords[key]) }
  return aWords
end
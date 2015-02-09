def combine_anagrams(words)
  results = Hash.new { |hash, key| hash[key] = [] }
  words.each { |word| (results[word.downcase.chars.sort.join] << word) }
  final = []
  results.each { |a, b| (final << b) }
  return final
end
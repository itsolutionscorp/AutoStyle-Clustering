def combine_anagrams(words)
  anagrams_hash = Hash.new { |hash, key| hash[key] = [] }
  words.each { |word| anagrams_hash[word.downcase.chars.sort.join].push(word) }
  anagrams = Array.new(0)
  anagrams_hash.each { |k, v| anagrams.push(v) }
  return anagrams
end
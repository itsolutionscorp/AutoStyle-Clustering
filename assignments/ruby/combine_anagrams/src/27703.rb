def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    temp = word.downcase.chars.sort.join
    anagrams[temp] ? (anagrams[temp] += 1) : (anagrams[temp] = 1)
  end
  results = Array.new(anagrams.length) { Array.new }
  words.each do |word|
    i = anagrams.keys.index(word.downcase.chars.sort.join)
    results[i].push(word)
  end
  return results
end
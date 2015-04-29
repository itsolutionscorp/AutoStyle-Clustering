def combine_anagrams(words = [])
  return [] if words.empty?
  hash = {}
  words.each do |word|
    anagram = word.downcase.split("").sort.join("")
    hash[anagram].nil? ? (hash[anagram] = [word]) : (hash[anagram].push(word))
  end
  return hash.values
end
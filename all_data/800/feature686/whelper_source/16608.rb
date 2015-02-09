def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    key = word.downcase.split("").sort.join
    anagram = anagrams[key]
    anagrams[key] = Array.new if (anagram == nil)
    anagrams[key].push(word)
  end
  anagrams_array = Array.new
  anagrams.keys.each { |key| anagrams_array.push(anagrams[key]) }
  return anagrams_array
end


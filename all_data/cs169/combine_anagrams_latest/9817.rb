def combine_anagrams(words)
  anagrams = Hash.new { |hash, key| hash[key] = Array.new }
  words.each do |word|
    w = word.downcase
    anagrams[w.chars.sort].push(word)
  end
  p anagrams.values
end
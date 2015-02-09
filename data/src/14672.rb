def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    anagrams[word.downcase.chars.sort.join] ||= Array.new
    (anagrams[word.downcase.chars.sort.join] << word)
  end
  return anagrams.values
end
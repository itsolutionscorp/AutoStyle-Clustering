def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    letters = word.downcase.gsub(/[^a-z]/, "").split("").sort.join
    anagrams[letters] = Array.new unless anagrams.include?(letters)
    (anagrams[letters] << word)
  end
  anagrams.values
end
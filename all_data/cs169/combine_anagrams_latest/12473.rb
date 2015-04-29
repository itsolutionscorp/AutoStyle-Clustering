def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    lowercase = word.downcase
    letters = lowercase.split(//)
    sorted = letters.sort.join
    if !anagrams.has_key? sorted
      anagrams[sorted] = []
    end
    anagrams[sorted].push(word)
  end
  return anagrams.values
end

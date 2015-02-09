def is_anagrams?(word1, word2)
  (word1.downcase.split(//).sort == word2.downcase.split(//).sort)
end

def combine_anagrams(words)
  anagrams = []
  words.each do |aWord|
    x = anagrams.find { |s| is_anagrams?(s[0], aWord) }
    if (x == nil) then
      x = [aWord]
      anagrams.push(x)
    else
      x.push(aWord)
    end
  end
  anagrams
end


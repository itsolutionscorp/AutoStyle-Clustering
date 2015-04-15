def combine_anagrams(words)
  anagrams = []
  words.each do |word|
    group = anagrams.select {|g| anagrams?(g.first, word) }.first
    if group
      group << word
    else
      anagrams << [ word ]
    end
  end
  anagrams
end

def anagrams?(word1, word2)
  word1.upcase.chars.sort == word2.upcase.chars.sort
end

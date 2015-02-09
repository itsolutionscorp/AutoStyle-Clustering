def combine_anagrams(words)
  anagrams = Array.new
  words.each do |word|
    nomatch = 1
    anagrams.each do |anagram|
      if anagram[0].downcase.chars.sort.join == word.downcase.chars.sort.join then
        anagram << word
        nomatch = 0
        break
      end
    end
    if nomatch == 1
        anagrams << [word]
    end
  end
  anagrams
end

def combine_anagrams words
  anagrams = []
  bInsert = false
  words.each do |word|
    anagrams.each { |anagram| if anagram[0].downcase.chars.sort.join == word.downcase.chars.sort.join then anagram << word; bInsert = true; break; end }
    anagrams << [word] if bInsert == false
    bInsert = false
  end
  anagrams
end

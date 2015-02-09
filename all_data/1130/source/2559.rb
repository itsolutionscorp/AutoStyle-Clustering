def combine_anagrams(words)
  anagram_groups = []

  words.each do |word|
    found = false
    anagram_groups.each do |anagram|
      if anagram[0].downcase.chars.sort { |a,b| a.casecmp(b) }.join == word.downcase.chars.sort { |a,b| a.casecmp(b) }.join
        anagram << word
        found = true
      end
    end
    anagram_groups << [word] unless found
  end
  
  anagram_groups
end


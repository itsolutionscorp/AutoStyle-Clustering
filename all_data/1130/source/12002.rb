def are_anagrams?(word1, word2)
  word1.downcase.chars.sort.join == word2.downcase.chars.sort.join
end

def combine_anagrams(words)
  if words.length <= 1
    anagram_groups = words
  else
    anagram_groups = Array.new
    words.each do |w|
      puts w
      found = false
      anagram_groups.each do |g|
        if are_anagrams? g[0], w
          g << w
          found = true
        end
        
        break if found
      end
      
      if !found
        anagram_groups << [ w ]
      end
    end
  end
  
  anagram_groups
end


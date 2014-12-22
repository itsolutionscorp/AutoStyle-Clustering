def combine_anagrams(words)
   hash_anagrams = Hash.new
   words.each { |word|
      anagram = word.downcase.chars.sort.join.to_s
      printf("Anagram : %s\n", anagram)
      if (hash_anagrams.include?(anagram))
         hash_anagrams[anagram] = hash_anagrams[anagram] + [word]
      else
         hash_anagrams[anagram] = [word]
      end
   }
   index = 0
   arr_anagrams = Array.new
   hash_anagrams.each_value { |value|
      arr_anagrams[index] = value
      index += 1
   }
   arr_anagrams
end

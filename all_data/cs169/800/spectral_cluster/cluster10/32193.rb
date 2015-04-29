def anagram(s1,s2)
  return s1.chars.sort.join == s2.chars.sort.join
end

def combine_anagrams(words)
   anagrams = []
   i=0
   while (words.length > 1 ) do
      w1 = words.select { |x| anagram(x,words[0]) }
	  anagrams[i] = w1;
	  words = words - w1;
	  i = i + 1;
   end
   return anagrams;
end
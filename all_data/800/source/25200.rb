def combine_anagrams(words)
 anagram_hashcollector=Hash.new
 
 words.each do |word|
  anagram=word.downcase.split(/\s*/).sort.join
  
  if(anagram_hashcollector.has_key?(anagram))
   anagram_hashcollector[anagram].push(word)
  else
    anagram_hashcollector[anagram]=[word]
  end 
 
 end
 
 anagram_arraycollector=Array.new
 
 anagram_hashcollector.each_value do |anagram|
  anagram_arraycollector.push(anagram)
 end
 
 return anagram_arraycollector
end

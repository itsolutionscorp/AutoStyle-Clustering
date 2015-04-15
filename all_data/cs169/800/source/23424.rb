def combine_anagrams(words)

anagram = Hash.new{|h,k| h[k] = Array.new;}

words.each do |word|
  
  anagram[(word.downcase).chars.sort.join] << word 

end

return anagram.values

end

#puts combine_anagrams(["cars", "for", "potatoes", "racs", "four","scar", "creams", "scream"]).to_s


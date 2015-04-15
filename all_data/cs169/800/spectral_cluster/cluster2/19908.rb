def anagrams?(string1, string2)
  string1.downcase.chars.sort.join == string2.downcase.chars.sort.join
end

def combine_anagrams(words)
  words.map{|word1| words.select{|word2| anagrams?(word1, word2)}}.uniq
end

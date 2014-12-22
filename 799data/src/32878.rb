#An anagram is a word obtained by rearranging the letters of another word. For
#example, "rats", "tars" and "star" are an anagram group because they are made up of the same
#letters.
#Given an array of strings, write a method that groups them into anagram groups and returns
#the array of groups. Case doesn't matter in classifying string as anagrams (but case should be
#preserved in the output), and the order of the anagrams in the groups doesn't matter.
def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    ana = word.downcase.chars.sort.join
    if (!anagrams.has_key?(ana))
      anagrams[ana] = Array.new
    end
    anagrams[ana].push(word)
  end
  return anagrams.values
end



def anagram?(word1, word2)
  word1.downcase.chars.sort.join == word2.downcase.chars.sort.join
end

def combine_anagrams(words)  anagrams = [];
  words.each do |word|
    added = false;
    anagrams.each do |list|
      if anagram? word, list[0]
        list << word
        added = true;
        break
      end
    end
    if !added
      anagrams << [word]
    end
  end  
  anagramsend
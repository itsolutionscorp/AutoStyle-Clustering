class Anagram
  def initialize(word)
    @word = word
  end
  
  def match(words)
    anagrams = []
    
    # rearrange the word to make it easier to compare it against the input array
    match = @word.downcase.chars.sort.join
    
    words.each do |word|
      # only match if the words are not the same (regardless of case) and they contain the same characters.
      anagrams << word if word.downcase != @word.downcase && word.downcase.chars.sort.join == match
    end
    
    anagrams
  end
end

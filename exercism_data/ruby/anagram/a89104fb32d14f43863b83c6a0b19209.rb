class Anagram
  
  def initialize word
    @word = word.downcase
  end
  
  def match possible_anagrams
    same_letters = lambda { |possible_anagram| possible_anagram.downcase.chars.sort == @word.chars.sort }
    same_word    = lambda { |possible_anagram| possible_anagram.downcase == @word}

    possible_anagrams.reject(&same_word).select(&same_letters)
  end
  
end

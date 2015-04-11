class Anagram
  
  def initialize word
    @word = word.downcase
  end
  
  def match possible_anagrams
    same_letters = lambda { |possible_anagram| possible_anagram.downcase.chars.sort == @word.downcase.chars.sort }
    
    possible_anagrams.select do |possible_anagram|
      @word.size == possible_anagram.size
    end.select do |possible_anagram|
      possible_anagram.downcase != @word.downcase
    end.select(&same_letters)
  end
  
end

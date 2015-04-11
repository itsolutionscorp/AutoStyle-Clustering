class Anagram
  
  def initialize word
    @word = word.downcase  
  end
  
  def match possible_anagrams
    possible_anagrams.select do |possible_anagram|
      @word.size == possible_anagram.size
    end.select do |possible_anagram|
      possible_anagram.downcase != @word.downcase
    end.select do |possible_anagram|
      possible_anagram.downcase.chars.sort == @word.downcase.chars.sort
    end
  end
  
end

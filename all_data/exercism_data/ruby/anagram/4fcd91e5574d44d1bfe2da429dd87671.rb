class Anagram
  
  def initialize word
    @word = word.downcase  
  end
  
  def match possible_anagrams
    possible_anagrams.find_all do |possible_anagram|
      @word.size == possible_anagram.size
    end.find_all do |possible_anagram|
      possible_anagram.downcase != @word.downcase
    end.find_all do |possible_anagram|
      possible_anagram.downcase.split(//).sort == @word.downcase.split(//).sort
    end
  end
  
end

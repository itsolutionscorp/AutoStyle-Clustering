class Anagram
  def initialize word
    @word = word
  end
  
  def match word_list
    word_list.select do |anagram| 
      anagram.downcase.split(//).sort == @word.downcase.split(//).sort &&
      anagram.downcase != @word.downcase
    end
  end
end

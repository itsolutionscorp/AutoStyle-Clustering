class Anagram
  def initialize(word)
    @word   = word.downcase
    @value = word_value(word)
  end
  
  def match(words)
    words.keep_if {|a| anagram? a }
  end
  
  private
  
  def anagram?(word)
    (word_value(word) == @value) && (word.downcase != @word)
  end
  
  def word_value(word)
    word.downcase.chars.sort.join
  end
end

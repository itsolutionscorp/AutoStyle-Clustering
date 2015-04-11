class Anagram
  def initialize(word)
    @word   = word.downcase
    @sorted = sort(word)
  end
  
  def match(words)
    words.keep_if {|a| anagram? a }
  end
  
  private
  
  def anagram?(word)
    (sort(word) == @sorted) and (word.downcase != @word)
  end
  
  def sort(word)
    word.downcase.chars.sort.join
  end
end

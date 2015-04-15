class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(list)
    list.select{ |a_word| anagram?(a_word.downcase) }
  end
  
  private
  
  def anagram?(a_word)
    a_word.chars.sort == @word.chars.sort unless identical?(a_word)
  end
  
  def identical?(a_word)
    a_word == @word
  end  
  
end

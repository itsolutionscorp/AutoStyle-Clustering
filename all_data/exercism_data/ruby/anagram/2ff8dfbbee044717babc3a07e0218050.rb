class Anagram

  def initialize(word)
    @word = word
  end

  def match(list)
    list.select{ |w| is_anagram?(w) }.sort
  end
  
  private
  def is_anagram?(a_word)
    a_word.downcase.chars.sort.join == @word.downcase.chars.sort.join && a_word.downcase != @word.downcase
  end
  
end

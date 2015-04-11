class Anagram

  def initialize(word)
    @word = word
  end

  def match(list)
    list.select{ |w| is_anagram?(w.downcase, @word.downcase) }.sort
  end
  
  private
  def is_anagram?(a_word, o_word)
    a_word.chars.sort == o_word.chars.sort && a_word != o_word
  end
  
end

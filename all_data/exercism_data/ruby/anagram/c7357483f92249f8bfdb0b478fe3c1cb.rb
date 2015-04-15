class Anagram
  def initialize(start_word)
    @start_word = start_word.downcase
  end

  def match(test_words)
    test_words.select {|word| anagram?(word.downcase)}
  end

  private
  def anagram?(word)
    different?(word) && order_letters(word) == order_letters(@start_word)
  end

  def order_letters(word)
    word.chars.sort
  end

  def different?(word)
    @start_word != word
  end
end

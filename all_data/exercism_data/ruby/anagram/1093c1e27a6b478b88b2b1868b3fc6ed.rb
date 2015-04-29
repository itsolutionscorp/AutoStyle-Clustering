class Anagram
  def initialize(start_word)
    @start_word = start_word.downcase
  end

  def match(test_words)
    test_words.select {|word| anagram?(word.downcase)}
  end

  private
  def anagram?(word)
    if words_dont_match?(word)
      order_letters(word) == order_letters(@start_word)
    end
  end

  def order_letters(word)
    word.chars.sort
  end

  def words_dont_match?(word)
    @start_word != word
  end
end

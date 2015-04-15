class Anagram
  def initialize(start_word)
    @start_word = start_word
  end

  def match(test_words)
    test_words.keep_if {|word| anagram?(word)}
  end

  private
  def anagram?(word)
    if words_dont_match?(word)
      order_letters(word.downcase) == order_letters(@start_word.downcase)
    end
  end

  def order_letters(word)
    word.chars.sort
  end

  def words_dont_match?(word)
    @start_word.downcase != word.downcase
  end
end

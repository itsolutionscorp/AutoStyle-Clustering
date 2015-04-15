class Anagram
  def initialize(test_word)
    @test_word = test_word
  end

  def match(words)
    words.select{ |x| anagram?(x, @test_word) }
  end

  private

  def anagram?(word_1, word_2)
    sanitized_word(word_1) == sanitized_word(word_2)
  end

  def sanitized_word(word)
    word.downcase.split('').sort
  end
end

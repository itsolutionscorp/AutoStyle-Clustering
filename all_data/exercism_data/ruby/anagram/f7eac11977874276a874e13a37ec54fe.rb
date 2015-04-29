class Anagram
  def initialize(test_word)
    @test_word = test_word
  end

  def match(words)
    words.select{ |x| anagram?(x, @test_word) }
  end

  private

  def anagram?(word_1, word_2)
    to_sorted_letters(word_1) == to_sorted_letters(word_2)
  end

  def to_sorted_letters(word)
    word.downcase.split('').sort
  end
end

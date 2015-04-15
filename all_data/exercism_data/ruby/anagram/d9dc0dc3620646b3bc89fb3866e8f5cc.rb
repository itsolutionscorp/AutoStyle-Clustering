class Anagram

  def initialize(test_word)
    @test_word = test_word
  end

  def match(words)
    words.select { |word| anagram? word }
  end

  private

  attr_reader :test_word

  def anagram?(word)
     !equal_words?(word, test_word) && equal_chars?(word, test_word)
  end

  def equal_words?(first_word, second_word)
    first_word.downcase.eql? second_word.downcase
  end

  def equal_chars?(first_word, second_word)
    sort_chars(first_word).eql? sort_chars(second_word)
  end

  def sort_chars(word)
    word.downcase.chars.sort
  end

end

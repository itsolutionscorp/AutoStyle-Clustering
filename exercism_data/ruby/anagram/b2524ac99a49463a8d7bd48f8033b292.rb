class Anagram

  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(array_of_test_words)
    array_of_test_words.select do |test_word| 
      is_anagram?(test_word) && !is_same_word?(test_word)
    end
  end

  private
  def is_anagram?(test_word)
    sort_by_char(word) == sort_by_char(test_word.downcase)
  end

  def sort_by_char(test_word)
    test_word.chars.sort.join
  end

  def is_same_word?(test_word)
    word == test_word.downcase
  end

end

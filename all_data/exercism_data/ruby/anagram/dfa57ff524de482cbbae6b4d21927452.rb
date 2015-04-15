class Anagram
  def initialize(test_word)
    @test_word = test_word
  end

  def match(words)
    words.select{ |x| anagram?(x) }
  end

  private

  def anagram?(word)
    to_sorted_letters(word) == to_sorted_letters(@test_word)
  end

  def to_sorted_letters(word)
    @test_word == word ? @sorted_test_word ||= split_n_sort(word) : split_n_sort(word)
  end

  def split_n_sort(word)
    word.downcase.split('').sort
  end
end

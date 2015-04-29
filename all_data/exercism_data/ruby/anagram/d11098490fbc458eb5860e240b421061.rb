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
    if @test_word == word
      @sorted_test_word ||= sort_chars(word)
    else
      sort_chars(word)
    end
  end

  def sort_chars(word)
    word.downcase.chars.sort
  end
end

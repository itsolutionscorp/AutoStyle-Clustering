class Anagram
  def initialize(word)
    @word = word.downcase
    @letter_set = letter_set word
  end

  def match(test_list)
    test_list.select do |word|
      same_set(word) && not_identical(word)
    end
  end

  private

  def not_identical(word)
    word.downcase != @word
  end

  def same_set(word)
    letter_set(word) == @letter_set
  end

  def letter_set(word)
    word.downcase.chars.sort
  end
end

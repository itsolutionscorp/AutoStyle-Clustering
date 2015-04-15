class Anagram
  def initialize(phrase)
    @phrase = phrase
  end

  def match(words)
    words.select { |word| WordPair.new(@phrase, word).anagram? }
  end
end

class WordPair
  def initialize(first, second)
    @first, @second = first.downcase, second.downcase
  end

  def anagram?
    same_letters? && not_identical?
  end

  private
  def same_letters?
    sorted_letters_for(first) == sorted_letters_for(second)
  end

  def not_identical?
    first != second
  end

  def sorted_letters_for(word)
    word.chars.sort
  end

  def first
    @first
  end

  def second
    @second
  end
end

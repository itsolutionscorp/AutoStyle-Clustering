class Anagram
  def initialize(word)
    @word = word
  end

  def match(words = [])
    words.select(&anagram)
  end

  private

  def anagram
    ->(word) { SortedWord.new(word) == SortedWord.new(@word) }
  end
end

class SortedWord
  def initialize(word)
    @word = word
  end

  def ==(word)
    not identical?(word) and has_the_same_letters?(word)
  end

  def downcase
    @word.downcase
  end

  def sorted
    downcase.chars.sort
  end

  private

  def identical?(word)
    word.downcase == downcase
  end

  def has_the_same_letters?(word)
    word.sorted == sorted
  end
end

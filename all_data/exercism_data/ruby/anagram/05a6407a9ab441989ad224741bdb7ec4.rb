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
    downcase != word.downcase && sorted == word.sorted
  end

  def downcase
    @_downcase ||= @word.downcase
  end

  def sorted
    @_sorted ||= downcase.chars.sort
  end
end

class Anagram
  def initialize(word)
    @word = word
  end

  def match(words = [])
    words.select(&anagram)
  end

  private

  def anagram
    ->(word) { SortedWord.from(word) == SortedWord.from(@word) }
  end
end

class SortedWord
  def self.from(word)
    memo[word]
  end

  def self.memo
    @memo ||= Hash.new { |hash, key| hash[key] = new(key) }
  end

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
    @_sorted ||= sort(downcase)
  end

  private

  def sort(word)
    word.chars.sort
  end
end

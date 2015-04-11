class Phrase
  def initialize(phrase)
    @words ||= WordList.new(parse(phrase))
  end

  def word_count
    words.count
  end

  private

  def parse(phrase)
    phrase.scan(/\w+/)
  end

  def words
    @words
  end
end

class WordList
  attr_reader :words

  def initialize(words)
    @words = words
    @normalized_words ||= words.map(&:downcase)
  end

  def count
    normalized_words.inject({}) do |memo, word|
      memo[word] = normalized_words.count(word)
      memo
    end
  end

  private

  def normalized_words
    @normalized_words
  end
end

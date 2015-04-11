class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.inject Hash.new(0), &counter
  end

  private

  def words
    @phrase.split /\W+/
  end

  def counter
    ->(result, word) { result[word.downcase] += 1; result }
  end
end

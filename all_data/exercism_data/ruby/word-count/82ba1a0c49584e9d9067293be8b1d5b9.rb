class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object Hash.new(0), &counter
  end

  private

  def words
    @phrase.split /\W+/
  end

  def counter
    ->(word, result) { result[word.downcase] += 1 }
  end
end

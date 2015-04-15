class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object Hash.new(0), &counter
  end

  private

  def words
    @phrase.downcase.scan(/\w+/)
  end

  def counter
    ->(word, result) { result[word] += 1 }
  end
end

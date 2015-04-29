class Phrase
  def initialize(phrase)
    @_phrase = phrase
  end

  def word_count
    words.each_with_object default, &counter
  end

  private

  def default
    Hash.new(0)
  end

  def words
    @_phrase.downcase.scan(/\w+/)
  end

  def counter
    ->(word, result) { result[word] += 1 }
  end
end

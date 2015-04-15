class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, accumulator|
      accumulator[word] = accumulator[word] + 1
    end
  end

private

  attr_accessor :phrase

  def normalized
    phrase.downcase
  end

  def words
    normalized.scan(/\w+/)
  end

end

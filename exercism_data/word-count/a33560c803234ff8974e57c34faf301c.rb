class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    normalized_words.inject(Hash.new(0)) do |accumulator, word|
      accumulator[word] += 1
      accumulator
    end
  end

  private

  def normalized_words
    @normalized_words ||= @phrase.downcase.scan(/\w+/)
  end
end

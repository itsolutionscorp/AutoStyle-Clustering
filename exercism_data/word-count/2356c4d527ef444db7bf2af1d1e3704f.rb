class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    tokens.each_with_object(Hash.new(0)) { |token, words|
      words[token] += 1
    }
  end

  private

  def tokens
    phrase.scan(/\w+/).map(&:downcase)
  end
end

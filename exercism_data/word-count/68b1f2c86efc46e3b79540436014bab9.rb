class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    tokens.inject(Hash.new(0)) do |h, token|
      h[token] += 1; h
    end
  end

  def tokens
    @phrase.split(/[^\w]+/).map(&:downcase)
  end

end

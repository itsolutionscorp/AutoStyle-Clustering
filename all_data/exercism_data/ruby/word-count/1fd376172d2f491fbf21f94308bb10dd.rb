class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    tokens.each_with_object(Hash.new(0)) { |token, words|
      i = word_index(token)
      words[i] += 1
    }
  end

  private

  def tokens
    phrase.scan(/\w+/)
  end

  def word_index(token)
    token.downcase
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    tokens.each_with_object(Hash.new(0)) { |token, words|
      word = token.downcase
      words[word] += 1
    }
  end

  private

  attr_reader :phrase

  def tokens
    phrase.scan(/\w+/)
  end
end

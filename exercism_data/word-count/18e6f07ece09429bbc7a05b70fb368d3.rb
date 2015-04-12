class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    tokens.each_with_object(Hash.new(0)) do |token, counts|
      counts[normalize(token)] += 1
    end
  end

  private

  def tokens
    @phrase.scan(/\w+/)
  end

  def normalize(token)
    token.downcase
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    normalized_tokens.each_with_object(Hash.new(0)) do |token, counts|
      counts[token] += 1
    end
  end

  private

  def normalized_tokens
    @phrase.downcase.scan(/\w+/)
  end
end

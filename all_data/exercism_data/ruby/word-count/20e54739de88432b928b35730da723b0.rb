class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.downcase.scan(/[\w']+/)
  end

  def word_count
    phrase.reduce(Hash.new(0)) { |count, word| count[word] += 1; count }
  end
end

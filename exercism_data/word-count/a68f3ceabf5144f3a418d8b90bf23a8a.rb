class Phrase

  attr_reader :phrase
  private :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.reduce(Hash.new(0)) { |count, word| count[word] += 1 ; count }
  end

  private

  def words
    phrase.downcase.scan(/\w+/)
  end
end

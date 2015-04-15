class Phrase
  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words_array = stripped_phrase.split(' ').map(&:downcase)
    words_array.reduce({}) { |hash, word| hash.merge(word => words_array.count(word)) }
  end

  private
  def stripped_phrase
    @phrase.gsub(/[^A-Za-z0-9 ']/, ' ')
  end
end

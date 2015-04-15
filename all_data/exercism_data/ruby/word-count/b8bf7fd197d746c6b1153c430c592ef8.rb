class Phrase

  attr_accessor :sentence

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    @word_count ||= perform_calculation
  end

  private

  def perform_calculation
    downcased_words.inject(Hash.new(0)) { |cache, words|
      cache[words] += 1
      cache
    }
  end

  def downcased_words
    words.map(&:downcase)
  end

  def words
    sentence.scan(/\w+/)
  end
end

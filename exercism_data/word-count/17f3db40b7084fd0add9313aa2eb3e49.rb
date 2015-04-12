class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    unique_words.each_with_object(Hash.new(0)) { |word, counts|
      counts[word] += 1
    }
  end

  private

  def unique_words
    phrase.scan(/\w+/).map(&:downcase)
  end
end

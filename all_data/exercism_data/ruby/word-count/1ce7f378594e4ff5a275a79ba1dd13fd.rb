class Phrase
  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @counted_words ||= count_words
  end

  private

  def count_words
    counted_words = Hash.new(0)
    normalize_phrase(@phrase).scan(/\w+/) { |word| counted_words[word] += 1 }
    counted_words
  end

  def normalize_phrase phrase
    phrase.to_s.downcase
  end
end

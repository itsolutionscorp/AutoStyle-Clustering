class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    count_words_in(phrase.downcase)
  end

  private

  def count_words_in(phrase)
    words = %r{\w+}
    phrase.scan(words).each_with_object(Hash.new(0)) do |word, word_counts|
      word_counts[word] += 1
    end
  end

end

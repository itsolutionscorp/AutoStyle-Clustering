class Phrase
  attr_reader :text, :word_count
  private :text

  def initialize(text)
    @text       = text
    @word_count = count_words
  end

  def count_words
    countable_fragments.inject({}) { |frequencies, fragment|
      frequencies[fragment] = (frequencies[fragment] || 0) + 1
      frequencies
    }
  end

  def countable_fragments
    text.scan(/\w+/).map(&:downcase)
  end
end

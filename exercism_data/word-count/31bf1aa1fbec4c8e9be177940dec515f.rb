class Phrase
  attr_reader :text, :word_count
  private :text

  def initialize(text)
    @text       = text
    @word_count = count_words
  end

  def count_words
    frequencies = Hash.new(0)
    countable_fragments.each { |fragment| frequencies[fragment] += 1 }
    frequencies
  end

  def countable_fragments
    text.scan(/\w+/).map(&:downcase)
  end
end

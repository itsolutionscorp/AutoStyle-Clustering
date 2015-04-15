class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    reset_word_frequencies
    count_word_frequencies
    word_frequencies
  end

  private

  def reset_word_frequencies
    @word_frequencies = Hash.new(0)
  end

  def count_word_frequencies
    words.each { |word| add_word_frequency(word) }
  end

  def words
    text.split(word_separator_regexp)
  end

  def word_separator_regexp
    /[^0-9a-z-A-Z]+/
  end

  def add_word_frequency(word)
    word_frequencies[normalize_word(word)] += 1
  end

  def normalize_word(word)
    word.downcase
  end

  attr_reader :text, :word_frequencies
end

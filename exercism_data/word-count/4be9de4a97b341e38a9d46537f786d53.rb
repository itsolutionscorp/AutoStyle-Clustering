class Phrase
  NON_WORD_CHARACTERS = /\W+/

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    words.inject(Hash.new(0)) { |word_counts, word| count_word(word_counts, word) }
  end

  def words
    phrase.split(NON_WORD_CHARACTERS)
  end

  def count_word(word_counts, word)
    word_counts[word] += 1
    word_counts
  end

  private

  attr_reader :phrase
end

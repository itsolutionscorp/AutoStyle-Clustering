class Phrase
  NON_WORD_CHARACTERS = /\W+/

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, word_counts| word_counts[word] += 1 }
  end

  def words
    phrase.split(NON_WORD_CHARACTERS)
  end

  private

  attr_reader :phrase
end

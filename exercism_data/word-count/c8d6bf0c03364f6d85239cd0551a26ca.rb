class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= Hash[words_and_counts]
  end

  private

  def words_and_counts
    words
      .group_by {|word| word}
      .map {|word, matches| [word, matches.size]}
  end

  def words
    phrase.downcase.split(/\W+/)
  end
end

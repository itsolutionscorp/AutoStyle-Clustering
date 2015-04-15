class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_groups = normalized_words.group_by { |w| w }
    word_groups.merge(word_groups) { |word, words| words.length }
  end

  private

  def normalized_words
    @phrase.downcase.split(/\W+/)
  end

end

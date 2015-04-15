class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count(normalized_words)
  end

  private

  def normalized_words
    @phrase.downcase.split(/\W+/)
  end

  def count(words)
    word_groups = words.group_by { |w| w }
    word_groups.merge(word_groups) { |word, words| words.length }
  end

end

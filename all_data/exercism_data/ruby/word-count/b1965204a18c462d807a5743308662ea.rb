class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    normalise_case
    words = split_words
    word_to_count(words)
  end

  private

  def normalise_case
    @phrase.downcase!
  end

  def word_to_count(words)
    word_groups = words.group_by { |w| w }
    word_groups.merge(word_groups) { |word, words| words.length }
  end

  def split_words
    @phrase.split(/\W+/)
  end

end

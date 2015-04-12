class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = split_words
    words.delete_if(&:empty?)
    words.map(&:downcase!)

    word_to_count(words)
  end

  private

  def word_to_count(words)
    word_groups = words.group_by { |w| w }
    word_groups.merge(word_groups) { |word, words| words.length }
  end

  def split_words
    @phrase.split(split_characters_regexp)
  end

  def split_characters_regexp
    /,|:|!|&|@|\$|%|\^|&| /
  end

end

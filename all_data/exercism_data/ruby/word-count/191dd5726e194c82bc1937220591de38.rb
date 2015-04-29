class Phrase
  attr_reader :words

  def initialize(words)
    @words = words.downcase.split(/\W+/)
  end

  def word_count
    grouped_words.inject({}) { |hash, (word, list)| hash.merge(word => list.count) }
  end

  private
  def grouped_words
    words.group_by(&:to_s)
  end
end

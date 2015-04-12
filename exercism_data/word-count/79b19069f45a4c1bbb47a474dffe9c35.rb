class Phrase
  attr_reader :words

  def initialize(words)
    @words = words.downcase
                  .split(/\W/)
                  .reject(&:empty?)
  end

  def word_count
    words.group_by(&:to_s)
         .inject({}) { |hash, (word, list)| hash.merge(word => list.count) }
  end
end

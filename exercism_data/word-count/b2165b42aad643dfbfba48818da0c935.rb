class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    hashify(words
            .group_by(&:downcase)
            .flat_map(&COUNT_WORDS))
  end

  private

  def words
    @phrase.scan(/\w*/)
    .reject(&:empty?)
  end

  COUNT_WORDS = ->((word, words)) { [word, words.size] }

  def hashify array
    Hash[*array]
  end
end

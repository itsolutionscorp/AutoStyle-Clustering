class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    Hash[words
         .group_by(&:downcase)
         .map(&COUNT_WORDS)]
  end

  private

  def words
    @phrase.scan(/\w+/)
  end

  COUNT_WORDS = ->((word, words)) { [word, words.size] }
end

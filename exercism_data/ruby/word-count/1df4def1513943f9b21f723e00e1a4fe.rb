class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = words
      .group_by(&:downcase)
      .map{|word, words| [word, words.count]}
    Hash[counts]
  end

  # don't count apostrophe as punctuation
  def words
    @phrase.split(/[^A-Za-z0-9']+/)
  end
end

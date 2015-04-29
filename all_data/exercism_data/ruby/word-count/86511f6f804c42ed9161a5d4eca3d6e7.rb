class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.inject({}) do |counts, word|
      counts[word] = counts.fetch(word, 0) + 1
      counts
    end
  end

  private

  def words
    @phrase.scan(/(?:\w|')+/).map { |w| w.downcase }
  end
end

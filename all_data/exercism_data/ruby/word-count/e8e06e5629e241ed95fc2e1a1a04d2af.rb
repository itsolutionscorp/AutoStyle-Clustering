class Phrase
  def initialize(utterance)
    @utterance = utterance
  end

  def word_count
    words.reduce({ }) { |count, w| count[w] = (count[w] || 0) + 1; count }
  end

private

  def cleaned
    @utterance.downcase.gsub(/[^0-9a-z' ]/, ' ')
  end

  def words
    cleaned.split
  end
end

class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = {}
    @phrase.downcase.scan(/\w+/).each do |w|
      counts[w] = counts[w].nil? ? 1 : counts[w] + 1
    end
    counts
  end

end

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = get_words
    counts = Hash.new(0)
    words.each do |w|
      counts[w] += 1
    end
    counts
  end

  def get_words
    normalize.scan(/\w+/)
  end

  def normalize
    @phrase.downcase
  end
end

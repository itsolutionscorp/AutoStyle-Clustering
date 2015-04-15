class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash[ words.uniq.map { |word| [word, 0]} ]
    words.each do |word|
      counts[word] += 1
    end
    counts
  end

  private

  def words
    without_punctuation.split(' ').map { |w| w.strip.downcase }
  end

  def without_punctuation
    @phrase.gsub(',', ' ').gsub(/[^\w\s']/, '')
  end

end

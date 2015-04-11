class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
    words.each { |word| counts[word] += 1 }
    counts
  end

  def words
    @phrase.gsub(/[^\w|']/, ' ').split.map(&:downcase)
  end
end

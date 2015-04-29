class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    words = @phrase.gsub(/[^'\w\s]/, ' ').split
    counts = words.uniq.map { |x| [x, words.count(x)] }
    Hash[*counts.flatten]
  end
end

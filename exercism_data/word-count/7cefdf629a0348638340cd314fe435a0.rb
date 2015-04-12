class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    counts = Hash.new(0)

    words.each do |word|
      counts[word] += 1 if word
    end

    counts
  end

  def words
    @phrase.scan(/[\w|\']+/)
  end
end

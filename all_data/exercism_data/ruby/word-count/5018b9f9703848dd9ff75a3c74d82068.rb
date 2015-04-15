class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    counts = Hash.new 0
    words.each { |word| counts[word] += 1 }
    counts
  end

  private

  def words
    @phrase.downcase.scan %r{[\w']+}
  end

end

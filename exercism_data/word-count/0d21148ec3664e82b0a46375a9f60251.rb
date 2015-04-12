class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    counts = Hash.new 0
    words.each_with_object(counts) { |word, hash| hash[word] += 1 }
  end

  private

  def words
    @phrase.downcase.scan %r{[\w']+}
  end

end

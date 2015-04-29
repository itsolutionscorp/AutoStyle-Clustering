class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    counter = Hash.new 0
    words.each.with_object(counter) { |word, h| h[word] += 1 }
  end

  private
  def words
    @phrase.downcase.scan /\w+/
  end
end

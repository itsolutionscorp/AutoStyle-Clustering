class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    counter = Hash.new 0
    words.each.with_object(counter) { |word, h| h[word.downcase] += 1 }
  end

  private
  def words
    @phrase.scan /\w+/
  end
end

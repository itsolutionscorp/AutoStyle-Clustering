class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)){ |w,h| h[w] += 1 }
  end

  private

  def words
    @phrase.downcase.scan(/\w+/)
  end

end

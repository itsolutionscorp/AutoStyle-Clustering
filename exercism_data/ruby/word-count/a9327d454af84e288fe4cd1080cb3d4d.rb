class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count(words)
  end

  private

  def words
    @phrase.scan(/[\w']+/)
  end

 
  def count(words)
    words.each_with_object(Hash.new(0)) { |word, h| h[word.downcase] += 1 }
  end

end

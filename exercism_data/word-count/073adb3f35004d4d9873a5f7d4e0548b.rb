class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    result = Hash.new(0)
    words.map {|word| result[word] += 1 }
    result
  end

  private

  def words
    @phrase.downcase.scan(/\w+/)
  end

end

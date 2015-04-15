class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, accumulator|
      accumulator[word] += 1
    end
  end

private

  def words
    normalized.scan(/\w+/)
  end

  def normalized
    phrase.downcase
  end
  
  def phrase
    @phrase
  end

end

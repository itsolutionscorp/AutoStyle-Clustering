class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    occurrences = Occurrences.new
    words.each { |word| occurrences << normalize(word) }
    occurrences.result
  end

  def words
    @phrase.scan(/[\w]+(?:'\w)?/)
  end

  private

  def normalize(word)
    word.downcase
  end
end

class Occurrences
  attr_reader :result

  def initialize
    @result = Hash.new(0)
  end

  def <<(key)
    result[key] += 1
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Occurrences.new) { |word, occurrences| occurrences << word }.result
  end

  def words
    @phrase.scan(/[\w]+(?:'\w)?/).map { |word| normalize word }
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

class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, occurrences| occurrences[word] += 1 }
  end

  def words
    normalize(@phrase).scan(/\w+(?:'\w)?/)
  end

  private

  def normalize(string)
    string.downcase
  end
end

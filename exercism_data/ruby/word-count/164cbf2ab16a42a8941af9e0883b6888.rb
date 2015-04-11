class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, count| count[word] += 1 }
  end

  private
  def words
    phrase.downcase.scan(/\w+/)
  end
end

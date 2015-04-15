class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.to_s.downcase
  end

  def word_count
    counter = Hash.new 0
    words.each_with_object(counter) { |word, acc| acc[word] += 1 }
  end

  def words
    phrase.scan /[a-zA-Z0-9]+/
  end
end

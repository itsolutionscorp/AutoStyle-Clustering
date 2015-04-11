class Phrase
  def initialize(phrase)
    @phrase = String.new(phrase)
  end

  def word_count
    normalized_words.each_with_object(Hash.new(0)) { |word, count| count[word] += 1 }
  end

  private 

  def normalized_words
    @phrase.downcase.scan(/\w+/)
  end

end

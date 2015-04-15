class Phrase
  def initialize(phrase)
    @phrase = phrase.to_s
  end

  def word_count
    normalized_words.each_with_object(Hash.new(0)) { |word, count| count[word] += 1 }
  end

  private 

  def normalized_words
    @phrase.downcase.scan(/\w+/)
  end

end

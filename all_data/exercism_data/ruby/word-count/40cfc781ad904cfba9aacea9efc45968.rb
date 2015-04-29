class CaseInsensitiveTokenizer
  def initialize(phrase)
    @phrase = phrase
  end

  def tokens
    @phrase.downcase.scan /\w+/
  end

end

class Phrase
  def initialize(phrase)
    @words = CaseInsensitiveTokenizer.new(phrase).tokens
  end

  def word_count
    @words.each_with_object(Hash.new(0)) { |word, count| count[word]++ }
  end

end

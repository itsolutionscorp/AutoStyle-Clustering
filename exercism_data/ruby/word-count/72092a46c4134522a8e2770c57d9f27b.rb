class Phrase

  def initialize(words)
    @words = words
  end

  def word_count
    frequency ||= histogram(tokenize(@words))
  end

  def tokenize(string)
    string.downcase.scan(/[\w']+/)
  end

  def histogram(tokens)
    tokens.each_with_object(Hash.new(0)) { |token, hash| hash[token] += 1 }
  end

end

class Tokenizer
  attr_reader :tokens

  def initialize(string)
    @tokens = string.downcase.split(/\W+/)
  end

end

class Phrase

  def initialize(string)
    @word_count = Hash.new(0)
    @tokens = Tokenizer.new(string).tokens
  end

  def word_count
    @tokens.each do |token|
      @word_count[token] += 1
    end
    @word_count
  end

end

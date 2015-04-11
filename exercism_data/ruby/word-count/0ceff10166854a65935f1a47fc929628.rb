class Phrase
  attr_accessor :string

  def initialize(string)
    @string = string
  end

  def word_count
    tokenizer.tokens.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  def tokenizer
    Tokenizer.new(string.downcase)
  end
end

class Tokenizer
  attr_reader :string

  def initialize(string)
    @string = string
  end

  def tokens
    string.scan(/\w+/)
  end
end

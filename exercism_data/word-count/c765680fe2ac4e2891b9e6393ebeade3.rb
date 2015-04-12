class Phrase
  attr_accessor :string

  def initialize(string)
    @string = string
  end

  def word_count
    tokenizer.tokens.each_with_object(Hash.new(0)) do |word, hash|
      hash[word.downcase] += 1
    end
  end

  def tokenizer
    Tokenizer.new(string)
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

class Phrase
  attr_accessor :string

  def initialize(string)
    @string = string
  end

  def word_count
    tokenizer.tokens.each_with_object(WordCount.new) { |word, word_count|
      word_count.increment(word.downcase)
    }
  end

  def tokenizer
    Tokenizer.new(string)
  end
end

class WordCount < Hash
  def increment(word)
    self[word] = current_value(word) + 1
  end

  def current_value(word)
    fetch(word, 0)
  end
end

class Tokenizer
  attr_reader :string

  def initialize(string)
    @string = string
  end

  def tokens
    string.split(/\W/).reject { |token| token.empty? }
  end
end

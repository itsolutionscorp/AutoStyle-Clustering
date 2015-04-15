class Phrase

  WORD_REGEXP = /\W+/

  def initialize(string)
    @words = Tokenizer.new(string, WORD_REGEXP).tokens
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word, word_count|
      word_count[word.downcase] += 1
    end
  end

end

class Tokenizer

  attr_reader :tokens

  def initialize(string, regexp)
    @tokens = string.split(regexp)
  end

end

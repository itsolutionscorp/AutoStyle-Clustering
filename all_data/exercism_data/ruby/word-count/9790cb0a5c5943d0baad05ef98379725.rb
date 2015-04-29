class Tokenizer
  attr_reader :tokens

  def initialize(string, regexp)
    @tokens = string.split(regexp)
  end

end

class Phrase

  WORD_REGEXP = /\W+/

  def initialize(string)
    @words = Tokenizer.new(string, WORD_REGEXP).tokens
  end

  def word_count
    word_count = Hash.new(0)
    @words.each do |word|
      word_count[word.downcase] += 1
    end
    word_count
  end

end

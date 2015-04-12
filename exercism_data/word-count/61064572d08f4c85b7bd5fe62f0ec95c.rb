class Phrase
  def initialize(text)
    @text = text || ""
  end

  def word_count
    tokenize_string(text).inject(Hash.new(0)) do |result, token|
      result[token] += 1
      result
    end
  end

private
  attr_reader :text

  NON_WORD = /\W+/

  def tokenize_string(input)
    input.downcase.split(NON_WORD)
  end
end

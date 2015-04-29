class Phrase
  def initialize(text)
    @text = text || ""
  end

  def word_count
    results = Hash.new(0)
    tokenize_string(text) do |token|
      results[token] += 1
    end
    results
  end

private
  attr_reader :text

  def tokenize_string(input, &token_block)
    input.downcase.scan(/\w+/, &token_block)
  end
end

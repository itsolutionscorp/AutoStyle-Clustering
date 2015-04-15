class Phrase
  def initialize(text)
    @tokenizer = TextTokenizer.new(text)
  end

  def word_count
    @tokenizer.tokenize.each_with_object(Hash.new(0)) { |token, count| count[token] += 1 }
  end
end

class TextTokenizer
  def initialize(text)
    @original   = text
    @normalized = @original.downcase
  end

  def tokenize
    @tokenized ||= @normalized.gsub(",", " ").split(" ").map { |token| token.slice(/\w+/) }.compact
  end
end

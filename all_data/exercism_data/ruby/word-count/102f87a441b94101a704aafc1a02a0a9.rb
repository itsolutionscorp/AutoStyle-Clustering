class Phrase
  def initialize(text)
    @tokenizer = TextTokenizer.new(text)
  end

  def word_count
    count = {}

    @tokenizer.tokenize.each { |word| count[word] = count.fetch(word, 0) + 1 }

    count
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
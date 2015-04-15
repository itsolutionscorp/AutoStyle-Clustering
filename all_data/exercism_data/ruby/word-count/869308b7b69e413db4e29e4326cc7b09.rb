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
    @text = text.downcase
  end

  def tokenize
    @tokenized ||= remove_punctuation_from(exploded_text)
  end

  private

  def exploded_text
    @text.gsub(",", " ").split(" ")
  end

  def remove_punctuation_from(tokens)
    tokens.map { |token| token.slice(/\w+/) }.compact
  end
end

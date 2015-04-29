class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @word_count = {}
    tokens = tokenize(phrase.downcase)
    count_words(tokens)
  end

  def count_words(tokens)
    tokens.each do |token|
      word = strip_punctuation(token)
      next if word.empty?
      @word_count[word] ||= 0
      @word_count[word] += 1
    end
  end

  def tokenize(phrase)
    comma_or_whitespace = /[,\s]\s*/
    phrase.split(comma_or_whitespace)
  end

  def strip_punctuation(token)
    token.gsub(/[[:punct:]]/, '')
  end
end

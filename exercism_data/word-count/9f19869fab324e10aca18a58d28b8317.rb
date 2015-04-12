class Tokenizer
  def initialize(sentence)
    @sentence = sentence
  end

  def valid_tokens
    sentence.downcase.scan(/\w+/)
  end

  private
  attr_reader :sentence
end

class WordCounter
  attr_reader :counts

  def initialize(counts=Hash.new(0))
    @counts = counts
  end

  def count(word)
    self.class.new(counts.merge({word => increment(word)}))
  end

  private

  def increment(word)
    counts[word] += 1
  end
end

class Phrase
  def initialize(sentence, histogram=WordCounter, tokenizer=Tokenizer)
    @tokenizer = tokenizer.new(sentence)
    @histogram = histogram
  end

  def word_count
    normalized_words.reduce(counter) do |counter, word|
      counter.count(word)
    end.counts
  end

  private
  attr_reader :tokenizer, :histogram

  def normalized_words
    tokenizer.valid_tokens
  end

  def counter
    histogram.new
  end
end

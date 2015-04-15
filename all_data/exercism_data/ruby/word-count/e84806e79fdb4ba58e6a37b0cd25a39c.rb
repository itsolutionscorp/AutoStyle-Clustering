class Tokeniser
  def initialize(sentence)
    @sentence = sentence
  end

  def valid_tokens
    remove_invalid_chars.downcase.split(' ')
  end

  private
  attr_reader :sentence

  def remove_invalid_chars
    sentence.gsub(/[^[:alnum:]]/, ' ')
  end
end

class WordCounter
  attr_reader :counts

  def initialize
    @counts = Hash.new(0)
  end

  def seen(word)
    @counts.merge({word => increment(word)})
  end

  private

  def increment(word)
    @counts[word] += 1
  end
end

class Phrase
  def initialize(sentence)
    @tokeniser = Tokeniser.new(sentence)
  end

  def word_count
    normalized_words.reduce(counter) do |counter, word|
      counter.seen(word)
      counter
    end.counts
  end

  private
  attr_reader :tokeniser

  def normalized_words
    tokeniser.valid_tokens
  end

  def counter
    WordCounter.new
  end
end

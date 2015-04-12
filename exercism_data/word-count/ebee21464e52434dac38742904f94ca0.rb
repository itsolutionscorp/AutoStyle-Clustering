class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    WordCounter.build(text).to_hash
  end
end

class WordCounter
  def self.build(text, tokenizer=Tokenizer)
    counter = WordCounter.new
    tokenizer = tokenizer.new(text)

    tokenizer.tokens.each do |word|
      counter << word
    end

    counter
  end

  def initialize
    @words = Hash.new { |hash, key| hash[key] = 0 }
  end

  def <<(word)
    words[word] += 1
  end

  def [](word)
    words[word]
  end

  def to_hash
    @words
  end

  private

  def words
    @words
  end
end

class Tokenizer
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def tokens
    text.scan(/[0-9a-z']+/i).map(&:downcase)
  end
end

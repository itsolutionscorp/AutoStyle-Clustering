class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def word_count
    WordCounter.count(self)
  end
end

class WordCounter

  def self.count(phrase, tokenizer=Tokenizer)
    counter = WordCounter.new
    tokenizer = tokenizer.new(phrase)

    tokenizer.tokens.each do |word|
      counter.add_word(word)
    end

    counter.word_count
  end

  def initialize
    @words = Hash.new { |hash, key| hash[key] = 0 }
  end

  def add_word(word)
    word_count[word] += 1
  end

  def word_count
    @words
  end
end

class Tokenizer
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def tokens
    return_tokens = split_into_tokens.map {|token| sanitize(token) }
    remove_empty_tokens(return_tokens)
  end

  private

  def remove_empty_tokens(tokens)
    tokens.reject(&:empty?)
  end

  def split_into_tokens
    phrase.text.split(/\s|,/)
  end

  def sanitize(word)
    word.downcase!
    remove_special_characters(word)
  end

  def remove_special_characters(word)
    word.gsub(/[^0-9a-z']/i,'').strip
  end

end

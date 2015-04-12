class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    WordsCounter.run(phrase)
  end
end

class WordsCounter
  attr_reader :words

  def self.run(words)
    new(words).run
  end

  def initialize(words)
    @words = words
  end

  def run
    Hash.new(0).tap do |words_count|
      find_each_word do |word|
        words_count[word] += 1
      end
    end
  end

  def find_each_word
    words.scan(/(\w+)/) do |match|
      yield normalize_match(match)
    end
  end

  def normalize_match(match)
    match[0].downcase
  end
end

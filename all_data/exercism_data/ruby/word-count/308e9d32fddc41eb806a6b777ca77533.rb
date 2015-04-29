class Phrase

  attr_reader :storage

  def initialize(phrase)
    words = WordScanner.scan(phrase)
    @storage = WordStorage.new(words)
  end

  def word_count
    storage.words
  end

end

module WordScanner

  def self.scan(phrase)
    phrase.scan(/\w+/).map(&:downcase)
  end

end

class WordStorage

  attr_accessor :words

  WORD_INC_COUNT = 1

  def initialize(words)
    @words = store(words)
  end

  private

  def store(words)
    words.each_with_object(Hash.new(0)) do |word, storage|
      storage[word] += WORD_INC_COUNT
    end
  end

end

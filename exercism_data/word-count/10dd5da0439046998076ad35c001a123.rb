class Phrase
  def initialize(text)
    text.downcase!
    
    @phrase = FlavourFactory.from text
    
    @word_count = {}

    @phrase.each_word do |word|
      _ensure word
      increment word
    end
  end

  attr_reader :word_count

  private

  def _ensure(word)
    @word_count[word] = 0 unless contains? word
  end

  def increment(word)
    @word_count[word] += 1
  end

  def contains?(word); @word_count[word]; end
end

class FlavourFactory
  class << self
    def from(text)
      (contains_whitespace?(text) ? Vanilla : Raspberry).new(text)
    end

    private

    def contains_whitespace?(text)
      text.match /\s/
    end
  end
end

module Flavour
  attr_reader :words

  def each_word(&block)
    @words.each {|word| yield word} if block_given?
  end
end

class Vanilla
  include Flavour

  def initialize(text)
    @words = text.gsub(/[^\w\s']/, '').split /\s+/
  end
end

class Raspberry
  include Flavour

  def initialize(text)
    @words = text.split /,/
  end
end

class Phrase
  attr_reader :word_count
  attr_accessor :phrase

  WORD = /[\w']+/

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= wc
  end

  private

  def wc
    @phrase.scan(WORD).reduce(CounterHash.new) { |a, e| a << e.downcase }.to_h
  end
end

class CounterHash
  def initialize
    @db = {}
  end

  def <<(el)
    @db.include?(el) ? (@db[el] += 1) : (@db[el] = 1)
    self
  end

  def to_h
    @db.clone
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase
    @counter = CounterFactory.create
  end

  def word_count
    @counter.get_counts(@phrase)
  end
end

class CounterFactory
  def self.create
    Counter.new ->(phrase) { phrase.split(/\W+/).map(&:downcase) }
  end
end

class Counter
  def initialize(splitter)
    @splitter = splitter
  end

  def get_counts(phrase)
    @splitter.call(phrase).reduce(Hash.new(0)) { |result,word|
      result[word] += 1
      result
    }
  end
end

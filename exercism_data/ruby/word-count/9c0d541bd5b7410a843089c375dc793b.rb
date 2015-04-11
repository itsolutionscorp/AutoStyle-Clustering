class Phrase
  attr_reader :counter, :text
  private     :counter

  def initialize(text, opts={})
    @text      = text
    @counter   = Counter.new(enumerable)
    @separator = opts[:separator]
  end

  def word_count
    counter.call
  end

  private

  def enumerable
    text.split(separator).map(&:downcase).to_enum
  end

  def separator
    @separator ||= /\W+/
  end

end

class Counter
  attr_reader :enumerable

  def initialize(enumerable)
    @enumerable = enumerable
  end

  def call
    counts
  end

  private

  def counts
    @counts ||= new_counting_hash.tap do |hash|
      enumerable.each { |element| hash[element] += 1 }
    end
  end

  def new_counting_hash
    Hash.new { |hash, key| hash[key] = 0 }
  end

end

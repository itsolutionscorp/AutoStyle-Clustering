class Tally
  def initialize
    @running_total = Hash.new(0)
  end

  def mark(value)
    @running_total[value] += 1
  end

  def to_hash
    @running_total
  end
end

class Phrase
  def initialize(string)
    @string = string
  end

  def word_count
    tally = Tally.new
    each_word {|word| tally.mark(word) }
    tally.to_hash
  end

  private

  WORD = /\w+/
  def each_word(&block)
    @string.downcase.scan(WORD) {|word| block.call(word) }
  end
end

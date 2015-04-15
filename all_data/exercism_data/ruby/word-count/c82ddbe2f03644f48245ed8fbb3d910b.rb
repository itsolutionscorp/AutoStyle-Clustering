class Phrase

  attr_reader :words

  def initialize(message)
    @words = simplify(message)
  end

  def word_count
    counts = Hash.new(0)
    words.each { |word| counts[word] += 1 }
    return counts
  end

  private

  def simplify(message = '')
    message.downcase.scan(/\w+/)
  end
end

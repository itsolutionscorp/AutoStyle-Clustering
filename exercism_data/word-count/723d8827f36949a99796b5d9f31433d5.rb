class Phrase

  attr_reader :words

  def initialize(message)
    @words = message.downcase.scan(/\w+/)
  end

  def word_count
    counts = Hash.new(0)
    words.each do |word|
      counts[word] += 1
    end
    return counts
  end
end

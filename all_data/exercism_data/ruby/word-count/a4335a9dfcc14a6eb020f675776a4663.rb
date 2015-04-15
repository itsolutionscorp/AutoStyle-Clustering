class Phrase

  attr_reader :words

  def initialize(message)
    @words = message_to_words(message)
  end

  def word_count
    counts = Hash.new(0)
    words.each { |word| counts[word] += 1 }
    return counts
  end

  private

  def message_to_words(message)
    message.downcase.scan(/\w+/)
  end
end

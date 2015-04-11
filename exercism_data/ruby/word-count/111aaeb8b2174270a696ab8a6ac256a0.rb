class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    counts = Hash.new(0)
    @words.downcase.split(/[^\w']+/).each { |word| counts[word] += 1 }
    counts
  end
end

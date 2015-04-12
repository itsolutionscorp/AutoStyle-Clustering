class Phrase

  def initialize(phrase)
    @words = phrase.downcase.split(/[^\w']+/)
  end

  def word_count
    counts = Hash.new(0)
    @words.each { |word| counts[word] += 1 }
    counts
  end
end

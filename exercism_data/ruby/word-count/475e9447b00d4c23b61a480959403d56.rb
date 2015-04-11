class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
    @phrase.downcase.split(/[^\w']+/).each { |word| counts[word] += 1 }
    return counts
  end
end

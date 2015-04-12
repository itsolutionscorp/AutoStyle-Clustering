class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
    @phrase.downcase.scan(/[\w']+/).each{ |word| counts[word] += 1 }
    counts
  end

end

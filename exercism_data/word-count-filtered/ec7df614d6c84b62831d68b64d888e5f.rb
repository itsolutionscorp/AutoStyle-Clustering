class Phrase
  attr_reader :phrase

  def initialize(phr)
    @phrase = phr
  end

  def word_count
    counts = Hash.new(0)
    @phrase.downcase.scan(/([a-z0-9']+\b)/).flatten.each { |x| counts[x] += 1 }

    counts
  end
end

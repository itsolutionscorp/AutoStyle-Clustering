class Phrase
  def initialize(phrase)
    @words = Words.from_phrase(phrase)
  end

  def word_count
    count = Hash.new(0)
    @words.each { |w| count[w] += 1 }
    count
  end
end

class Words
  def self.from_phrase(phrase)
    phrase.split(/\W+/).each { |w| w.downcase! }
  end
end

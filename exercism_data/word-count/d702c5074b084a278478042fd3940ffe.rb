class Phrase
  def initialize(phrase)
    @words = Words.from_phrase(phrase)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) { |w, h| h[w] += 1 }
  end
end

class Words
  def self.from_phrase(phrase)
    phrase.split(/\W+/).each { |w| w.downcase! }
  end
end

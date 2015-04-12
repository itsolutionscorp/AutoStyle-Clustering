class Phrase
  attr_reader :phrase

  def word_count
    Words.new(phrase).count
  end
  
  class Words
    def initialize(phrase)
      @words = phrase.split(/\W+/).each { |w| w.downcase! }
    end

    def count
      count = Hash.new(0)
      @words.each { |w| count[w] += 1 }
      count
    end
  end
end

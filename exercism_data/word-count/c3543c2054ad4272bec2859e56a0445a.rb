class Phrase
  def initialize(phrase)
    self.phrase = phrase
  end

  def word_count
    phrase.downcase
          .scan(/[\w']+/)
          .each_with_object(Hash.new 0) { |word, counts|
            counts[word] += 1
          }
  end

  private

  attr_accessor :phrase
end

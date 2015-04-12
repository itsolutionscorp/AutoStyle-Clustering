class Phrase
  WORDS = /[\w']+/

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
    @phrase.downcase.scan(WORDS) { |word| counts[word] +=1 }
    counts
  end
end

class Phrase
  attr_accessor :phrase

  def initialize phrase
    self.phrase = phrase
  end

  def word_count
    word_list = phrase.downcase.scan /[\w']+/
    counts = Hash.new 0
    word_list.each { |word| counts[word] += 1 }
    counts
  end
end

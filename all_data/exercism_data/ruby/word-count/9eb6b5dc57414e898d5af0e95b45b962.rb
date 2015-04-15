class Phrase

  attr_accessor :phrase
  attr_accessor :word_count

  def initialize(word)
    self.phrase = word
    self.word_count = {}
    process_count
  end

  private

  def process_count
    self.phrase.split(/[^\w']+/).each do |w|
      self.word_count[w.downcase] ||= 0
      self.word_count[w.downcase] += 1
    end
  end

end

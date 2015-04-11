class Phrase
  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase
  end
  
  def word_count
    counts = Hash.new
    counts.default = 0
    @phrase.downcase.gsub(/[^0-9a-z ]/, '').split.each do |word|
      counts[word] += 1
    end
    return counts
  end
end

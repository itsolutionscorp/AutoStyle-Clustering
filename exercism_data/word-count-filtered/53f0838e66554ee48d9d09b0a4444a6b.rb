class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
    @phrase.gsub(/[^\w']/, ' ').split(/\s+/).each do |word|
      counts[word.downcase] += 1
    end
    counts
  end
end

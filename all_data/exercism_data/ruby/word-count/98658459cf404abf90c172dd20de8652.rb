class Phrase
  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new
    counts.default = 0
    clean_phrase.split.each do |word|
      counts[word] += 1
    end
    return counts
  end

private

  def clean_phrase
    @phrase.downcase.gsub(/[^0-9a-z ]/, '')
  end

end

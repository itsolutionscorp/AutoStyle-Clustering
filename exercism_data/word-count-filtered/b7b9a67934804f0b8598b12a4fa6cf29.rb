class Phrase
  attr_accessor :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
    clean_phrase = phrase.downcase.gsub(/[^'\w]/, " ")
    clean_phrase.split.each { |key| counts[key] += 1}
    counts
  end
end

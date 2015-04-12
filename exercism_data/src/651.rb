class Phrase
  attr_reader :words

  def initialize(phrase)
    @words = phrase.gsub(/[^A-Za-z0-9]/, ' ').split.map { |w| w.downcase }
  end

  def word_count
    count = Hash.new(0)
    words.each { |word| count[word] += 1 }
    return count
  end
end

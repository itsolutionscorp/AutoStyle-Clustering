class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @phrase = phrase.downcase
    @word_count = Hash.new(0)
    @phrase.scan(/[\w']+/).each { |word| @word_count[word] += 1 }
  end
end

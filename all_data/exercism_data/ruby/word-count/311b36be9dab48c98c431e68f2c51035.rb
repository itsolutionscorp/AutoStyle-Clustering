class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @words = phrase.downcase.scan(/[\w']+/)
    @word_count = Hash.new(0)
    @words.each { |word| @word_count[word] += 1 }
  end
end

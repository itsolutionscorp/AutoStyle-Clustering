class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @word_count = Hash.new 0
    phrase.downcase.split(/[^\w']+/).each { |word| @word_count[word] += 1 }
  end
end

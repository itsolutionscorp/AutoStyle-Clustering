class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  attr_reader :phrase

  def word_count
    unless @word_count
      @word_count = Hash.new(0)
      phrase.scan(/[\w']+/) { |word| word_count[word.downcase] += 1 }
    end
    @word_count
  end
end

class Phrase
  attr_accessor :phrase, :word_count

  def initialize(phrase)
    @phrase = phrase.
              split(/[^\w']+/).
              map{ |word| word.downcase }

    @word_count = Hash.new(0)

    @phrase.each { |word| @word_count[word] += 1 }
  end
end

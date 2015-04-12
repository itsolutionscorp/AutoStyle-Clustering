class Phrase
  attr_accessor :phrase, :word_count

  def initialize(phrase)
    @phrase = phrase.
              split(/[^\w']+/).
              map{ |word| word.downcase }

    @word_count = {}

    @phrase.each do |word|
      @word_count[word] ? @word_count[word] += 1 : @word_count[word] = 1
    end
  end
end

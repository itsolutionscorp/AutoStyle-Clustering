class Phrase

  attr_reader :phrase, :words

  def initialize(phrase)
    @phrase = phrase
    @words = phrase.downcase.scan(/\w+/)
  end

  def word_count
    words.inject(Hash.new(0)) do |h, word|
      h[word] += 1; h
    end
  end

end

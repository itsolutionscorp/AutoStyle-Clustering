class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    pieces.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  def pieces
    phrase.downcase.scan(/\w+/)
  end

end

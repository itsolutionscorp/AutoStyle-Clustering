class Phrase

  attr_reader :phrase, :pieces

  def initialize(phrase)
    @phrase = phrase
    @pieces  = split_phrase
  end

  def word_count
    pieces.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  def split_phrase
    phrase.downcase.scan(/\w+/)
  end

end

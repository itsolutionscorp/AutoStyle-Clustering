class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase.downcase.scan(/[\w']+/).inject(Hash.new(0)) do |a, e|
      a[e] += 1
      a
    end
  end

end

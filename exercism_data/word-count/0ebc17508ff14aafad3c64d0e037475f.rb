class Phrase
  
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.scan(/(?:[\w']+|\d+)/).inject(Hash.new(0)) do |h, w|
      h[w.downcase] += 1
      h
    end
  end

end

class Phrase

  def initialize(phrase)
    @words  = phrase.downcase.scan(/\w+/)
  end

  def word_count
    h = {}
    @words.map{ |w| h[w] = @words.count(w) }
    return h
  end

end

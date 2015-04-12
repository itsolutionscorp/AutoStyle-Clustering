class Phrase

  def initialize(phrase)
    @words  = phrase.downcase.scan(/\w+/)
  end

  def word_count
    @words.each_with_object({}) { |w, h| h[w] = @words.count(w) }
  end

end

class Phrase
  def initialize(word)
    @word = word.downcase
  end

  def word_count
    @word.scan(/\w+/).each.with_object(Hash.new(0)) { |w, h| h[w] += 1 }
  end
end

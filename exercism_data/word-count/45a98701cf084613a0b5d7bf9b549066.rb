class Phrase

  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    words = @phrase.scan(/[\w\']+/)
    words.each_with_object(Hash.new(0)) do |w, hash|
      hash[w] += 1
    end
  end

end

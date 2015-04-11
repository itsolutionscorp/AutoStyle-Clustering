class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = normalize(@phrase)
    histogram(words)
  end

  private
    def normalize(phrase)
      phrase.downcase.scan(/\w+/)
    end

    def histogram(words) 
      words.each_with_object(Hash.new(0)) {|w, h| h[w] += 1 }
    end
end

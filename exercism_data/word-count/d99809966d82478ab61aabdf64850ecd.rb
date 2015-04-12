class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = find_all_words(@phrase)
    histogram(words)
  end

  private
    def find_all_words(phrase)
      phrase.downcase.scan(/\w+/)
    end

    def histogram(words) 
      words.each_with_object(Hash.new(0)) {|w, h| h[w] += 1 }
    end
end

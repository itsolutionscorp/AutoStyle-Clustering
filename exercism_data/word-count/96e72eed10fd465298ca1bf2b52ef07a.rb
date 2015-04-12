class Phrase
  def initialize(phrase)
    @phrase = phrase
    @words  = normalize_words
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |w, h|
      h[w] += 1
    end
  end

  private

  def normalize_words
    @phrase.downcase.scan(/\w+/)
  end
end

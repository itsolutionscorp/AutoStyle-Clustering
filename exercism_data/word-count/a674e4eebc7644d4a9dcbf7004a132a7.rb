class Phrase
  def initialize(phrase)
    @words = normalize(phrase)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |w, h|
      h[w] += 1
    end
  end

  private

  def normalize(phrase)
    phrase.downcase.scan(/\w+/)
  end
end

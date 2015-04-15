class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    get_words.each_with_object(Hash.new(0)) { |i, c| c[i] += 1 }
  end

  private

  def get_words
    @phrase.downcase.scan(/\w+/)
  end
end

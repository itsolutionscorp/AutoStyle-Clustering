class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    get_words(@phrase).each_with_object(Hash.new(0)) { |i, c| c[i] += 1 if i  }
  end

  private

  def get_words(phrase)
    phrase.downcase.scan(/\w+/)
  end
end

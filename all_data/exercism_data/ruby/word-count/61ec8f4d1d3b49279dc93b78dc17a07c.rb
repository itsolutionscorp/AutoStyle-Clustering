class Phrase

  attr_reader :word_count

  def initialize phrase
    @word_count = normalize(phrase).each_with_object(Hash.new(0)) { |word, counter| counter[word] += 1 }
  end

  private

  def normalize phrase
    phrase.downcase.scan(/\w+/)
  end

end
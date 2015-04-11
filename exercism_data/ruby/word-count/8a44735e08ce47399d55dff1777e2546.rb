class Array
  def frequencies
    self.each_with_object(Hash.new { |h,k| h[k] = 0 }) do |obj, frequencies|
      frequencies[obj] += 1
    end
  end
end

class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @word_count = parse_words(phrase).frequencies
  end

  private

  def parse_words(phrase)
    phrase.downcase.scan(/\w+'?\w*/)
  end
end

class Phrase
  attr_reader :raw_text
  attr_accessor :frequency

  WORD_DEFINITION = /['\w]+/

  def initialize(text)
    @raw_text  = text

    # A little trick that defaults each key's value to 0
    @frequency = Hash.new(0)
  end

  def word_count
    @word_count ||= generate_histogram
  end

  private

  def generate_histogram
    raw_text.scan(WORD_DEFINITION).each do |word|
      frequency[word.downcase] += 1
    end

    frequency
  end
end

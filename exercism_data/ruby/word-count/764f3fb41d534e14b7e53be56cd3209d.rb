class Phrase
  attr_reader :text
  attr_accessor :frequency

  def initialize(text)
    @text = text
    @frequency = Hash.new(0)
  end

  def word_count
    @word_count ||= generate_histogram
  end

  private

  def generate_histogram
    text.scan(word_definition).each do |word|
      frequency[word.downcase] += 1
    end

    frequency
  end

  def word_definition
    /['\w]+/
  end
end
